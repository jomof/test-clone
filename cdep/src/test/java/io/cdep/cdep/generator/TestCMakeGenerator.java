package io.cdep.cdep.generator;

import io.cdep.cdep.BuildFindModuleFunctionTable;
import io.cdep.cdep.ResolvedManifests;
import io.cdep.cdep.ast.finder.FunctionTableExpression;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.fail;

public class TestCMakeGenerator {
  final private GeneratorEnvironment environment = new GeneratorEnvironment(
      new File("./test-files/TestCMakeGenerator/working"), null, false, false);

  @Test
  public void testBoost() throws Exception {
    BuildFindModuleFunctionTable builder = new BuildFindModuleFunctionTable();
    builder.addManifest(ResolvedManifests.boost());
    FunctionTableExpression table = builder.build();
    String result = new CMakeGenerator(environment, table).create();
    System.out.printf(result);
  }

  @Test
  public void testAllResolvedManifests() throws Exception {
    Map<String, String> expected = new HashMap<>();
    expected.put("admob", "Reference com.github.jomof:firebase/app:2.1.3-rev8 was not found");
    boolean unexpectedFailures = false;
    for (ResolvedManifests.NamedManifest manifest : ResolvedManifests.all()) {
      BuildFindModuleFunctionTable builder = new BuildFindModuleFunctionTable();
      builder.addManifest(manifest.resolved);
      String expectedFailure = expected.get(manifest.name);
      try {
        FunctionTableExpression table = builder.build();
        new CMakeGenerator(environment, table).generate();
        if (expectedFailure != null) {
          fail("Expected failure");
        }
      } catch (RuntimeException e) {
        if (expectedFailure == null || expectedFailure.equals(e.getMessage())) {
          System.out.printf("expected.put(\"%s\", \"%s\")\n", manifest.name, e.getMessage());
          unexpectedFailures = true;
        }
      }
    }
    if (unexpectedFailures) {
      throw new RuntimeException("Unexpected failures. See console.");
    }
  }
}
