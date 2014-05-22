#!/bin/bash
find -mindepth 1 -name "pom.xml" -exec perl -pi -e 's!<version>1.0-SNAPSHOT</version>!<version>0.3.3</version>!o' {} \;
find -mindepth 1 -name "footer.jspf" -exec perl -pi -e 's!0.3.2!0.3!o' {} \;
