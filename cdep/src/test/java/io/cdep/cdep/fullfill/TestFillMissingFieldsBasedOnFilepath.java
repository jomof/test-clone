package io.cdep.cdep.fullfill;

import static com.google.common.truth.Truth.assertThat;
import io.cdep.cdep.utils.CDepManifestYmlUtils;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;
import org.junit.Test;

public class TestFillMissingFieldsBasedOnFilepath {

  @Test
  public void testABI() {
    String body = "coordinate:\n"
        + "  groupId: com.github.jomof\n"
        + "  artifactId: firebase/app\n"
        + "  version: ${version}\n"
        + "interfaces:\n"
        + "  headers:\n"
        + "    file: ${source}/include/firebase/app.h -> firebase/app.h\n"
        + "\n"
        + "android:\n"
        + "  archives:\n"
        + "  - file: firebase_cpp_sdk/libs/android/arm64-v8a/stlport/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/armeabi-v7a/gnustl/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/armeabi/c++/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/mips64/cxx/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/mips/c++/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/x86_64/c++/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android/x86/c++/libapp.a\n";

    CDepManifestYml manifest = CDepManifestYmlUtils.convertStringToManifest(body);
    manifest = new FillMissingFieldsBasedOnFilepath().visitCDepManifestYml(manifest);
    assertThat(manifest.android.archives[0].abi).isEqualTo("arm64-v8a");
    assertThat(manifest.android.archives[0].runtime).isEqualTo("stlport");
    assertThat(manifest.android.archives[0].lib).isEqualTo("libapp.a");
    assertThat(manifest.android.archives[1].abi).isEqualTo("armeabi-v7a");
    assertThat(manifest.android.archives[1].runtime).isEqualTo("gnustl");
    assertThat(manifest.android.archives[2].abi).isEqualTo("armeabi");
    assertThat(manifest.android.archives[3].runtime).isEqualTo("c++");
    assertThat(manifest.android.archives[3].abi).isEqualTo("mips64");
    assertThat(manifest.android.archives[4].runtime).isEqualTo("c++");
    assertThat(manifest.android.archives[4].abi).isEqualTo("mips");
    assertThat(manifest.android.archives[5].abi).isEqualTo("x86_64");
    assertThat(manifest.android.archives[6].abi).isEqualTo("x86");
  }

  @Test
  public void testPlatform() {
    String body = "coordinate:\n"
        + "  groupId: com.github.jomof\n"
        + "  artifactId: firebase/app\n"
        + "  version: ${version}\n"
        + "interfaces:\n"
        + "  headers:\n"
        + "    file: ${source}/include/firebase/app.h -> firebase/app.h\n"
        + "\n"
        + "android:\n"
        + "  archives:\n"
        + "  - file: firebase_cpp_sdk/libs/android/arm64-v8a/c++/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android-21/arm64-v8a/c++/libapp.a\n"
        + "  - file: firebase_cpp_sdk/libs/android-9/arm64-v8a/c++/libapp.a\n";

    CDepManifestYml manifest = CDepManifestYmlUtils.convertStringToManifest(body);
    manifest = new FillMissingFieldsBasedOnFilepath().visitCDepManifestYml(manifest);
    assertThat(manifest.android.archives[0].platform).isEqualTo("12");
    assertThat(manifest.android.archives[1].platform).isEqualTo("21");
    assertThat(manifest.android.archives[2].platform).isEqualTo("9");
  }
}