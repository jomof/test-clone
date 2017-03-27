/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package io.cdep.cdep.resolver;

import io.cdep.cdep.utils.CDepManifestYmlUtils;
import io.cdep.cdep.yml.cdep.SoftNameDependency;
import io.cdep.cdep.yml.cdepmanifest.CDepManifestYml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.cdep.cdep.utils.Invariant.notNull;

public class LocalFilePathCoordinateResolver extends CoordinateResolver {

    @Override
    public ResolvedManifest resolve(ManifestProvider environment, SoftNameDependency dependency)
        throws IOException {
        String coordinate = dependency.compile;
      File local = new File(notNull(coordinate));
        if (!local.isFile()) {
            return null;
        }
        String content = new String(Files.readAllBytes(Paths.get(local.getCanonicalPath())));
        CDepManifestYml cdepManifestYml = CDepManifestYmlUtils.convertStringToManifest(content);
        CDepManifestYmlUtils.checkManifestSanity(cdepManifestYml);
        return new ResolvedManifest(local.getCanonicalFile().toURI().toURL(), cdepManifestYml);
    }


}
