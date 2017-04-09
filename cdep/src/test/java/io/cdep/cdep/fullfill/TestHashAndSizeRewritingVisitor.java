package io.cdep.cdep.fullfill;

import io.cdep.cdep.utils.CDepManifestYmlUtils;
import io.cdep.cdep.utils.FileUtils;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

/**
 * Created by jomof on 4/8/2017.
 */
public class TestHashAndSizeRewritingVisitor {
  @Test
  public void testBasic() throws IOException {
    CDepManifestYml before = CDepManifestYmlUtils.convertStringToManifest(
        FileUtils.readAllText(new File("../third_party/stb/cdep/cdep-manifest-divide.yml")));

    CDepManifestYml afterSubstitution = new SubstituteStringsRewritingVisitor()
        .replace("${wf}", new File("../third_party/stb").getAbsolutePath())
        .visitCDepManifestYml(before);

    File output = new File(".test-files/testHashAndSize").getAbsoluteFile();

    ZipFilesRewritingVisitor zipper = new ZipFilesRewritingVisitor(output);
    CDepManifestYml afterZipping = zipper.visitCDepManifestYml(afterSubstitution);

    assertThat(zipper.getLayoutFolder().isDirectory()).isTrue();
    assertThat(new File(zipper.getLayoutFolder(), "stb_divide.h.zip").isFile()).isTrue();
    assertThat(afterZipping.interfaces.headers.file).isEqualTo("stb_divide.h.zip");
    assertThat(afterZipping.interfaces.headers.include).isEqualTo("include");

    CDepManifestYml afterHashing = new HashAndSizeRewritingVisitor(zipper.getLayoutFolder())
        .visitCDepManifestYml(afterZipping);

    assertThat(afterHashing.interfaces.headers.sha256).isNotNull();
    assertThat(afterHashing.interfaces.headers.size).isNotNull();
  }
}