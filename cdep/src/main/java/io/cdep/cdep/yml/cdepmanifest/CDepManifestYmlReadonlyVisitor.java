package io.cdep.cdep.yml.cdepmanifest;

import io.cdep.cdep.Coordinate;
import io.cdep.cdep.pod.PlainOldDataReadonlyVisitor;

public class CDepManifestYmlReadonlyVisitor extends PlainOldDataReadonlyVisitor {

  public void visitHardNameDependency(String name, HardNameDependency value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitCoordinate(String name, Coordinate value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitCDepManifestYml(String name, CDepManifestYml node) {
    visitPlainOldDataObject(null, node);
  }

  public void visitHardNameDependencyArray(String name, HardNameDependency array[]) {
    visitArray(array, HardNameDependency.class);
  }

  public void visitAndroidArchiveArray(String name, AndroidArchive array[]) {
    visitArray(array, AndroidArchive.class);
  }

  public void visitiOSArchiveArray(String name, iOSArchive array[]) {
    visitArray(array, iOSArchive.class);
  }

  public void visitiOSPlatform(String name, iOSPlatform value) {
    visitPlainOldDataObject(null, value);
  }

  public void visitiOSArchitecture(String name, iOSArchitecture value) {
    visitPlainOldDataObject(null, value);
  }

  public void visitArchive(String name, Archive value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitAndroid(String name, Android value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitiOS(String name, iOS value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitAndroidArchive(String name, AndroidArchive value) {
    visitPlainOldDataObject(name, value);
  }

  public void visitiOSArchive(String name, iOSArchive value) {
    visitPlainOldDataObject(name, value);
  }
}
