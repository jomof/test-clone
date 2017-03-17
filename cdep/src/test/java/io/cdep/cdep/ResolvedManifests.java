package io.cdep.cdep;

import io.cdep.cdep.resolver.ResolvedManifest;
import io.cdep.cdep.utils.CDepManifestYmlUtils;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;
import java.net.MalformedURLException;
import java.net.URL;


public class ResolvedManifests {

  public static ResolvedManifest admob() throws MalformedURLException {
    String manifest = "coordinate:\n"
        + "  groupId: com.github.jomof\n"
        + "  artifactId: firebase/admob\n"
        + "  version: 2.1.3-rev8\n"
        + "dependencies:\n"
        + "  - compile: com.github.jomof:firebase/app:2.1.3-rev8\n"
        + "    sha256: 41ce110b24d2cfa26144b9df1241a4941c57e892f07eb91600103e53650ef0a8\n"
        + "archive:\n"
        + "  file: firebase-include.zip\n"
        + "  sha256: 26e3889c07ad841c5c9ff8b1ad86a575833bec1bb6f15719a527d52ced07a57f\n"
        + "  size: 93293\n"
        + "android:\n"
        + "  archives:\n"
        + "    - file: firebase-android-admob-cpp.zip\n"
        + "      sha256: 34c3cd109199cbccf7ebb1652a5dd66080c27d1448cfa3e6dd5c811aa30e283a\n"
        + "      size: 4990064\n"
        + "      ndk: r10d\n"
        + "      runtime: c++\n"
        + "      platform: 12\n"
        + "      abis: [arm64-v8a, armeabi, armeabi-v7a, mips, mips64, x86, x86_64]\n"
        + "      include: include\n"
        + "      lib: libadmob.a\n"
        + "    - file: firebase-android-admob-gnustl.zip\n"
        + "      sha256: 44cd6682f5f82735d4bf6103139e4179ed6c2b4ceec297fd04b129766e2a2a02\n"
        + "      size: 5294900\n"
        + "      ndk: r10d\n"
        + "      runtime: gnustl\n"
        + "      platform: 12\n"
        + "      abis: [arm64-v8a, armeabi, armeabi-v7a, mips, mips64, x86, x86_64]\n"
        + "      include: include\n"
        + "      lib: libadmob.a\n"
        + "    - file: firebase-android-admob-stlport.zip\n"
        + "      sha256: 0606618e53a06e00cd4c6775f49bd5474bccec68370691b35b12be2f1c89d755\n"
        + "      size: 5154330\n"
        + "      ndk: r10d\n"
        + "      runtime: stlport\n"
        + "      platform: 12\n"
        + "      abis: [arm64-v8a, armeabi, armeabi-v7a, mips, mips64, x86, x86_64]\n"
        + "      include: include\n"
        + "      lib: libadmob.a\n"
        + "example: |\n"
        + "  #include \"firebase/admob.h\"\n"
        + "  #include \"firebase/admob/types.h\"\n"
        + "  #include \"firebase/app.h\"\n"
        + "  #include \"firebase/future.h\"\n"
        + "  \n"
        + "  void test() {\n"
        + "    const char* kAdMobAppID = \"ca-app-pub-XXXXXXXXXXXXXXXX~NNNNNNNNNN\";\n"
        + "    firebase::admob::Initialize(\n"
        + "      *::firebase::App::Create(::firebase::AppOptions(), NULL /* jni_env */ , NULL /* activity */ ), \n"
        + "      kAdMobAppID);\n"
        + "  }\n";

    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }

  public static ResolvedManifest sqlite() throws MalformedURLException {
    String manifest = "coordinate:\n" +
        "  groupId: com.github.jomof\n" +
        "  artifactId: sqlite\n" +
        "  version: 0.0.0\n" +
        "android:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-cxx-platform-12.zip\n" +
        "    sha256: 45a104d61786eaf163b3006aa989922c5c04b8e787073e1cbd60c7895943162c\n" +
        "    size: 2676245\n" +
        "    runtime: c++\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-gnustl-platform-12.zip\n" +
        "    sha256: 5975eff815bd516b5da803f4921774ee38ec7d37fcb046bf2b3e078d920bd775\n" +
        "    size: 2676242\n" +
        "    runtime: gnustl\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-stlport-platform-12.zip\n" +
        "    sha256: b562331de4d7110349ec6ca2c7c888579f2bb56be095afce4671531809b2a894\n" +
        "    size: 2676242\n" +
        "    runtime: stlport\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-cxx-platform-21.zip\n" +
        "    sha256: 54ee95133dbddd4e2d76c572c0f4591aa4d7820f96f52566906f244c05d8bd9c\n" +
        "    size: 4346280\n" +
        "    runtime: c++\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-gnustl-platform-21.zip\n" +
        "    sha256: da9600b63f03dc9c11ac5b7c234212e16686e0b6874206626bc65f60f230f1af\n" +
        "    size: 4346366\n" +
        "    runtime: gnustl\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-stlport-platform-21.zip\n" +
        "    sha256: f2876bf59b2624b9adc44fd7758bee15fd0d782ddb6049e9e384e0a2b7a7c03f\n" +
        "    size: 4346303\n" +
        "    runtime: stlport\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "iOS:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-ios-platform-iPhone.zip\n" +
        "    sha256: 7126dfb6282a53c16cd648fcfca3bd8c3ac306def1b5bc8cefb3b82b459fca80\n" +
        "    size: 1293737\n" +
        "    platform: iPhoneOS\n" +
        "    sdk: 10.2\n" +
        "    architecture: armv7s\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-ios-platform-simulator.zip\n" +
        "    sha256: 266f16031afd5aef8adf19394fdcf946cb6a28d19a41b7db1ff87487733b91df\n" +
        "    size: 546921\n" +
        "    platform: iPhoneSimulator\n" +
        "    sdk: 10.2\n" +
        "    architecture: i386\n" +
        "example: |\n" +
        "  #include <sqlite3.h>\n" +
        "  void test() {\n" +
        "    sqlite3_initialize();\n" +
        "  }";
    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }

  public static ResolvedManifest sqliteiOS() throws MalformedURLException {
    String manifest = "coordinate:\n" +
        "  groupId: com.github.jomof\n" +
        "  artifactId: sqlite\n" +
        "  version: 0.0.0\n" +
        "iOS:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-ios-platform-iPhone.zip\n" +
        "    sha256: 7126dfb6282a53c16cd648fcfca3bd8c3ac306def1b5bc8cefb3b82b459fca80\n" +
        "    size: 1293737\n" +
        "    platform: iPhoneOS\n" +
        "    sdk: 10.2\n" +
        "    architecture: armv7s\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-ios-platform-simulator.zip\n" +
        "    sha256: 266f16031afd5aef8adf19394fdcf946cb6a28d19a41b7db1ff87487733b91df\n" +
        "    size: 546921\n" +
        "    platform: iPhoneSimulator\n" +
        "    sdk: 10.2\n" +
        "    architecture: i386\n" +
        "example: |\n" +
        "  #include <sqlite3.h>\n" +
        "  void test() {\n" +
        "    sqlite3_initialize();\n" +
        "  }";
    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }

  public static ResolvedManifest sqliteAndroid() throws MalformedURLException {
    String manifest = "coordinate:\n" +
        "  groupId: com.github.jomof\n" +
        "  artifactId: sqlite\n" +
        "  version: 0.0.0\n" +
        "android:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-cxx-platform-12.zip\n" +
        "    sha256: 45a104d61786eaf163b3006aa989922c5c04b8e787073e1cbd60c7895943162c\n" +
        "    size: 2676245\n" +
        "    runtime: c++\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-gnustl-platform-12.zip\n" +
        "    sha256: 5975eff815bd516b5da803f4921774ee38ec7d37fcb046bf2b3e078d920bd775\n" +
        "    size: 2676242\n" +
        "    runtime: gnustl\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-stlport-platform-12.zip\n" +
        "    sha256: b562331de4d7110349ec6ca2c7c888579f2bb56be095afce4671531809b2a894\n" +
        "    size: 2676242\n" +
        "    runtime: stlport\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-cxx-platform-21.zip\n" +
        "    sha256: 54ee95133dbddd4e2d76c572c0f4591aa4d7820f96f52566906f244c05d8bd9c\n" +
        "    size: 4346280\n" +
        "    runtime: c++\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-gnustl-platform-21.zip\n" +
        "    sha256: da9600b63f03dc9c11ac5b7c234212e16686e0b6874206626bc65f60f230f1af\n" +
        "    size: 4346366\n" +
        "    runtime: gnustl\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-stlport-platform-21.zip\n" +
        "    sha256: f2876bf59b2624b9adc44fd7758bee15fd0d782ddb6049e9e384e0a2b7a7c03f\n" +
        "    size: 4346303\n" +
        "    runtime: stlport\n" +
        "    platform: 21\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, arm64-v8a, x86, x86_64 ]\n" +
        "example: |\n" +
        "  #include <sqlite3.h>\n" +
        "  void test() {\n" +
        "    sqlite3_initialize();\n" +
        "  }";
    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }

  static ResolvedManifest emptyiOSArchive() throws MalformedURLException {
    String manifest = "coordinate:\n" +
        "  groupId: com.github.jomof\n" +
        "  artifactId: sqlite\n" +
        "  version: 0.0.0\n" +
        "android:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-android-cxx-platform-12.zip\n" +
        "    sha256: 45a104d61786eaf163b3006aa989922c5c04b8e787073e1cbd60c7895943162c\n" +
        "    size: 2676245\n" +
        "    runtime: c++\n" +
        "    platform: 12\n" +
        "    ndk: r13b\n" +
        "    abis: [ armeabi, armeabi-v7a, x86 ]\n" +
        "iOS:\n" +
        "  archives:\n" +
        "example: |\n" +
        "  #include <sqlite3.h>\n" +
        "  void test() {\n" +
        "    sqlite3_initialize();\n" +
        "  }";
    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }

  static ResolvedManifest emptyAndroidArchive() throws MalformedURLException {
    String manifest = "coordinate:\n" +
        "  groupId: com.github.jomof\n" +
        "  artifactId: sqlite\n" +
        "  version: 0.0.0\n" +
        "iOS:\n" +
        "  archives:\n" +
        "  - lib: libsqlite.a\n" +
        "    file: sqlite-ios-platform-iPhone.zip\n" +
        "    sha256: 45a104d61786eaf163b3006aa989922c5c04b8e787073e1cbd60c7895943162c\n" +
        "    platform: iPhoneOS\n" +
        "    size: 2676245\n" +
        "    sdk: 10.2\n" +
        "    architecture: armv7\n" +
        "android:\n" +
        "  archives:\n" +
        "example: |\n" +
        "  #include <sqlite3.h>\n" +
        "  void test() {\n" +
        "    sqlite3_initialize();\n" +
        "  }";
    CDepManifestYml yml = CDepManifestYmlUtils.convertStringToManifest(manifest);
    CDepManifestYmlUtils.checkManifestSanity(yml);
    return new ResolvedManifest(new URL("http://google.com/cdep-manifest.yml"), yml);
  }
}
