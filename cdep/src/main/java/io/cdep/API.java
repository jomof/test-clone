package io.cdep;

import io.cdep.cdep.generator.GeneratorEnvironment;
import io.cdep.cdep.utils.PlatformUtils;
import io.cdep.cdep.utils.ReflectionUtils;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static io.cdep.cdep.utils.Invariant.require;

/**
 * Methods meant to be used for calling back from CMake or ndk-build into CDep.
 */
public class API {

  /**
   * Get the location of java.exe that started this process.
   */
  static String getJvmLocation() {
    String java = System.getProperties().getProperty("java.home")
        + File.separator + "bin" + File.separator + "java";
    if (PlatformUtils.isWindows()) {
      java += ".exe";
      java = java.replace("\\", "/");
    }
    File result = new File(java);
    require(result.isFile(), "Expected to find java at %s but didn't", result);
    return java;
  }

  /**
   * Get a java command-line to call back into CDep.
   */
  private static List<String> callCDep(GeneratorEnvironment environment) throws MalformedURLException {
    List<String> result = new ArrayList<>();
    if (PlatformUtils.isWindows()) {
      result.add("\"" + getJvmLocation() + "\"");
    } else {
      result.add(platformQuote(getJvmLocation()));
    }
    result.add("-classpath");
    String classPath = ReflectionUtils.getLocation(API.class).getAbsolutePath().replace("\\", "/");
    if (!classPath.endsWith(".jar")) {
      String separator = PlatformUtils.isWindows() ? ";" : ":";
      // In a test environment need to include SnakeYAML since it isn't part of the unit test
      classPath = ReflectionUtils.getLocation(YAMLException.class).getAbsolutePath().replace("\\", "/")
          + separator + classPath;
    }
    result.add(platformQuote(classPath));
    result.add("io.cdep.CDep");
    result.add("--working-folder");
    result.add(platformQuote(environment.workingFolder.getAbsolutePath().replace("\\", "/")));

    return result;
  }

  private static String platformQuote(String file) {
    if (PlatformUtils.isWindows()) {
      return "\"" + file + "\"";
    }
    return file;
  }

  /**
   * Generate a call back to CDep.
   */
  public static List<String> generateCDepCall(
      GeneratorEnvironment environment,
      String ... args) throws MalformedURLException {
    List<String> result = new ArrayList<>();
    result.addAll(callCDep(environment));
    for (String arg : args) {
      result.add(arg);
    }
    return result;
  }
}
