package io.cdep.cdep;

import io.cdep.annotations.NotNull;
import io.cdep.cdep.ast.finder.FunctionTableExpression;
import io.cdep.cdep.ast.finder.ParameterExpression;
import org.junit.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static io.cdep.cdep.ast.finder.ExpressionBuilder.archive;
import static org.junit.Assert.fail;

public class TestInterpretingVisitor {
  @Test
  public void testNullInclude() throws Exception {
    new InterpretingVisitor().visit(archive(new URL("https://google.com"), "sha256",
        192L, null, null, null, null));
  }

  @Test
  public void testAllResolvedManifestsLinux() throws Exception {
    Map<String, String> expected = new HashMap<>();
    expected.put("archiveMissingSize", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("archiveMissingSha256", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("archiveMissingFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("sqliteiOS",
        "Abort: Target platform Linux is not supported by com.github.jomof:sqlite:3.16.2-rev33. Supported: Darwin");
    expected.put("sqlite",
        "Abort: Target platform Linux is not supported by com.github.jomof:sqlite:0.0.0. Supported: Android Darwin");
    expected.put("sqliteAndroid",
        "Abort: Target platform Linux is not supported by com.github.jomof:sqlite:3.16.2-rev33. Supported: Android");
    expected.put("admob",
        "Reference com.github.jomof:firebase/app:2.1.3-rev8 was not found, needed by com.github.jomof:firebase/admob:2.1.3-rev8");
    expected.put("singleABI",
        "Abort: Target platform Linux is not supported by com.github.jomof:sqlite:0.0.0. Supported: Android");
    expected.put("singleABISqlite",
        "Abort: Target platform Linux is not supported by com.github.jomof:sqlite:3.16.2-rev45. Supported: Android Darwin");
    expected.put("templateWithNullArchives", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("templateWithOnlyFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("indistinguishableAndroidArchives", "Abort: Target platform Linux is not supported by "
        + "com.github.jomof:firebase/app:0.0.0. Supported: Android");

    boolean unexpectedFailures = false;
    for (ResolvedManifests.NamedManifest manifest : ResolvedManifests.all()) {
      BuildFindModuleFunctionTable builder = new BuildFindModuleFunctionTable();
      builder.addManifest(manifest.resolved);
      String expectedFailure = expected.get(manifest.name);
      try {
        final FunctionTableExpression function = builder.build();
        new InterpretingVisitor() {
          @Override
          protected Object visitParameterExpression(@NotNull ParameterExpression expr) {
            if (function.globals.cdepExplodedRoot == expr) {
              return "exploded/root";
            }
            if (function.globals.cmakeSystemName == expr) {
              return "Linux";
            }
            if (function.globals.cmakeSystemVersion == expr) {
              return 21;
            }
            if (function.globals.cdepDeterminedAndroidAbi == expr) {
              return "x86";
            }
            if (function.globals.cdepDeterminedAndroidRuntime == expr) {
              return "c++_static";
            }
            if (function.globals.cmakeOsxSysroot == expr) {
              return "/iPhoneOS10.2.sdk";
            }
            if (function.globals.cmakeOsxArchitectures == expr) {
              return "i386";
            }
            return super.visitParameterExpression(expr);
          }
        }.visit(function);
        if (expectedFailure != null) {
          fail("Expected failure for " + manifest.name);
        }
      } catch (RuntimeException e) {
        if (!RuntimeException.class.equals(e.getClass())) {
          throw e;
        }
        if (expectedFailure == null || !expectedFailure.equals(e.getMessage())) {
          unexpectedFailures = true;
          System.out.printf("expected.put(\"%s\", \"%s\");\n", manifest.name, e.getMessage());
        }
      }

    }
    if (unexpectedFailures) {
      fail("Unexpected failures. See console.");
    }
  }

  @Test
  public void testAllResolvedManifestsAndroid() throws Exception {
    Map<String, String> expected = new HashMap<>();
    expected.put("sqliteLinux",
        "Abort: Target platform Android is not supported by com.github.jomof:sqlite:0.0.0. Supported: Linux");
    expected.put("sqliteLinuxMultiple",
        "Abort: Target platform Android is not supported by com.github.jomof:sqlite:0.0.0. Supported: Linux");
    expected.put("archiveMissingFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("admob",
        "Reference com.github.jomof:firebase/app:2.1.3-rev8 was not found, needed by com.github.jomof:firebase"
            + "/admob:2.1.3-rev8");
    expected.put("archiveMissingSize", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("archiveMissingSha256", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("sqliteiOS",
        "Abort: Target platform Android is not supported by com.github.jomof:sqlite:3.16.2-rev33. Supported: Darwin");
    expected.put("templateWithNullArchives", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("templateWithOnlyFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("indistinguishableAndroidArchives", "Abort: Android ABI x86 is not supported by com.github.jomof:firebase/app:0.0.0. Supported: arm64-v8a ");
    boolean unexpectedFailures = false;
    for (ResolvedManifests.NamedManifest manifest : ResolvedManifests.all()) {
      BuildFindModuleFunctionTable builder = new BuildFindModuleFunctionTable();
      builder.addManifest(manifest.resolved);
      String expectedFailure = expected.get(manifest.name);
      try {
        final FunctionTableExpression function = builder.build();
        new InterpretingVisitor() {
          @Override
          protected Object visitParameterExpression(@NotNull ParameterExpression expr) {
            if (function.globals.cdepExplodedRoot == expr) {
              return "exploded/root";
            }
            if (function.globals.cmakeSystemName == expr) {
              return "Android";
            }
            if (function.globals.cmakeSystemVersion == expr) {
              return 21;
            }
            if (function.globals.cdepDeterminedAndroidAbi == expr) {
              return "x86";
            }
            if (function.globals.cdepDeterminedAndroidRuntime == expr) {
              return "c++_static";
            }
            if (function.globals.cmakeOsxSysroot == expr) {
              return "/iPhoneOS10.2.sdk";
            }
            if (function.globals.cmakeOsxArchitectures == expr) {
              return "i386";
            }
            return super.visitParameterExpression(expr);
          }
        }.visit(function);
        if (expectedFailure != null) {
          fail("Expected failure");
        }
      } catch (RuntimeException e) {
        if (expectedFailure == null || !expectedFailure.equals(e.getMessage())) {
          unexpectedFailures = true;
          System.out.printf("expected.put(\"%s\", \"%s\");\n", manifest.name, e.getMessage());
        }
      }
    }
    if (unexpectedFailures) {
      fail("Unexpected failures. See console.");
    }
  }

  @Test
  public void testAllResolvedManifestsiOS() throws Exception {
    Map<String, String> expected = new HashMap<>();
    expected.put("sqliteLinuxMultiple",
        "Abort: Target platform Darwin is not supported by com.github.jomof:sqlite:0.0.0. Supported: Linux");
    expected.put("archiveMissingSha256", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("archiveMissingFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("archiveMissingSize", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("sqliteAndroid",
        "Abort: Target platform Darwin is not supported by com.github.jomof:sqlite:3.16.2-rev33. Supported: Android");
    expected.put("sqliteLinux",
        "Abort: Target platform Darwin is not supported by com.github.jomof:sqlite:0.0.0. Supported: Linux");
    expected.put("admob",
        "Reference com.github.jomof:firebase/app:2.1.3-rev8 was not found, needed by com.github.jomof:firebase/admob:2.1.3-rev8");
    expected.put("singleABI",
        "Abort: Target platform Darwin is not supported by com.github.jomof:sqlite:0.0.0. Supported: Android");
    expected.put("templateWithNullArchives", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("templateWithNullArchives", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("templateWithOnlyFile", "Abort: Archive in http://google.com/cdep-manifest.yml was malformed");
    expected.put("indistinguishableAndroidArchives", "Abort: Target platform Darwin is not supported by com.github.jomof:firebase/app:0.0.0. Supported: Android");
    boolean unexpectedFailures = false;
    for (ResolvedManifests.NamedManifest manifest : ResolvedManifests.all()) {
      final BuildFindModuleFunctionTable builder = new BuildFindModuleFunctionTable();
      builder.addManifest(manifest.resolved);
      String expectedFailure = expected.get(manifest.name);
      try {
        final FunctionTableExpression function = builder.build();
        new InterpretingVisitor() {
          @Override
          protected Object visitParameterExpression(@NotNull ParameterExpression expr) {
            if (function.globals.cdepExplodedRoot == expr) {
              return "exploded/root";
            }
            if (function.globals.cmakeSystemName == expr) {
              return "Darwin";
            }
            if (function.globals.cmakeSystemVersion == expr) {
              return 21;
            }
            if (function.globals.cdepDeterminedAndroidAbi == expr) {
              return "x86";
            }
            if (function.globals.cdepDeterminedAndroidRuntime == expr) {
              return "c++_static";
            }
            if (function.globals.cmakeOsxSysroot == expr) {
              return "/iPhoneOS10.2.sdk";
            }
            if (function.globals.cmakeOsxArchitectures == expr) {
              return new String[]{"i386"};
            }
            return super.visitParameterExpression(expr);
          }
        }.visit(function);
        if (expectedFailure != null) {
          fail("Expected failure");
        }
      } catch (RuntimeException e) {
        if (expectedFailure == null || !expectedFailure.equals(e.getMessage())) {
          unexpectedFailures = true;
          System.out.printf("expected.put(\"%s\", \"%s\");\n", manifest.name, e.getMessage());
        }
      }
    }
    if (unexpectedFailures) {
      fail("Unexpected failures. See console.");
    }
  }
}
