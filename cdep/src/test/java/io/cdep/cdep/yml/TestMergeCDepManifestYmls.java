package io.cdep.cdep.yml;

import io.cdep.cdep.ResolvedManifests;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;
import io.cdep.cdep.yml.cdepmanifest.MergeCDepManifestYmls;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;
import static junit.framework.TestCase.fail;

public class TestMergeCDepManifestYmls {

  @Test
  public void testJustCoordinate() throws MalformedURLException {
    try {
      MergeCDepManifestYmls.merge(ResolvedManifests.sqlite().cdepManifestYml,
          ResolvedManifests.admob().cdepManifestYml);
      fail("Expected exception");
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Manifests were different at artifactId.coordinate.[value]");
    }
  }

  @Test
  public void testMergeAndroidiOS() throws MalformedURLException {
    CDepManifestYml ios = ResolvedManifests.sqliteiOS().cdepManifestYml;
    CDepManifestYml android = ResolvedManifests.sqliteAndroid().cdepManifestYml;
    CDepManifestYml result = MergeCDepManifestYmls.merge(android, ios);
    assertThat(result.iOS.archives).hasLength(ios.iOS.archives.length);
    assertThat(result.android.archives).hasLength(android.android.archives.length);
  }

  @Test
  public void testTwoWayMerges() throws Exception {
    Set<String> commonDifferences = new HashSet<>();
    commonDifferences.add("Manifests were different at artifactId.coordinate.[value]");
    commonDifferences.add("Manifests were different at size.archive.[value]");
    commonDifferences.add("Manifests were different at file.archive.[value]");
    commonDifferences.add("Manifests were different at sha256.archive.[value]");
    Map<String, String> expected = new HashMap<>();
    boolean somethingUnexpected = false;
    for (ResolvedManifests.NamedManifest manifest1 : ResolvedManifests.all()) {
      for (ResolvedManifests.NamedManifest manifest2 : ResolvedManifests.all()) {
        String key = manifest1.name + "-" + manifest2.name;
        String expectedFailure = expected.get(key);
        try {
          MergeCDepManifestYmls.merge(manifest1.resolved.cdepManifestYml,
              manifest2.resolved.cdepManifestYml);
          if (expectedFailure != null) {
            fail("Expected a failure.");
          }
        } catch (RuntimeException e) {
          String actual = e.getMessage();
          if (!actual.equals(expectedFailure)) {
            if (!commonDifferences.contains(actual)) {
              // e.printStackTrace();
              System.out.printf("expected.put(\"%s\", \"%s\");\n", key, actual);
              somethingUnexpected = true;
            }
          }
        }
      }
    }

    if (somethingUnexpected) {
      throw new RuntimeException("Saw unexpected results. See console.");
    }
  }
}
