package io.cdep.cdep.utils;


import io.cdep.cdep.generator.GeneratorEnvironment;
import io.cdep.cdep.resolver.ResolvedManifest;
import io.cdep.cdep.resolver.Resolver;
import io.cdep.cdep.yml.cdep.SoftNameDependency;
import io.cdep.cdep.yml.cdepmanifest.Archive;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import static io.cdep.cdep.utils.Invariant.require;

public class EnvironmentUtils {

  /**
   * Returns the package level archive's include folder. Will throw an exception if there was no package level
   * archive.
   */
  public static File getPackageLevelIncludeFolder(GeneratorEnvironment environment, String coordinate)
      throws IOException, NoSuchAlgorithmException, URISyntaxException {
    ResolvedManifest resolved = resolveManifest(environment, coordinate);
    return getPackageLevelIncludeFolder(environment, coordinate, resolved);
  }

  /**
   * Returns the package level archive's include folder. Will throw an exception if there was no package level
   * archive.
   */
  static File getPackageLevelIncludeFolder(
      GeneratorEnvironment environment,
      String coordinate,
      ResolvedManifest resolved) throws URISyntaxException, MalformedURLException {
    CDepManifestYml manifest = resolved.cdepManifestYml;
    Archive archive = manifest.archive;
    require(archive != null, "'%s' does not have archive", coordinate);
    require(archive.include != null, "'%s' does not have archive.include", coordinate);
    require(archive.file != null, "'%s' does not have archive.include.file", coordinate);
    return new File(
        environment.getLocalUnzipFolder(
            manifest.coordinate,
            resolved.remote.toURI()
                .resolve(".").resolve(archive.file)
                .toURL()),
        archive.include);
  }

  /**
   * Return the resolved manifest or throw an exception.
   */
  public static ResolvedManifest resolveManifest(GeneratorEnvironment environment, String coordinate)
      throws IOException, NoSuchAlgorithmException {
    SoftNameDependency name = new SoftNameDependency(coordinate);
    ResolvedManifest resolved = new Resolver(environment).resolveAny(name);
    require(resolved != null, "Could not resolve '%s'", coordinate);
    return resolved;
  }
}
