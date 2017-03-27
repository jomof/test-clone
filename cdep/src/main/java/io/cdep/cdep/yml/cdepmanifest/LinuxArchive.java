package io.cdep.cdep.yml.cdepmanifest;

import org.jetbrains.annotations.Nullable;

public class LinuxArchive {
  @Nullable
  final public String file;
  @Nullable
  final public String sha256;
  @Nullable
  final public Long size;
  @Nullable
  final public String lib;
  final public String include;

  LinuxArchive() {
    this.file = null;
    this.sha256 = null;
    this.size = null;
    this.lib = null;
    this.include = "include";
  }

  public LinuxArchive(String file, String sha256, Long size, String lib, String include) {
    this.file = file;
    this.sha256 = sha256;
    this.size = size;
    this.lib = lib;
    this.include = include;
  }
}
