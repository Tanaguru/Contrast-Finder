#!/bin/bash
find -mindepth 1 -name "pom.xml" -exec perl -pi -e 's!<version>1.0-SNAPSHOT</version>!<version>0.3</version>!o' {} \;
find -mindepth 1 -name "footer.jspf" -exec perl -pi -e 's!1.0-SNAPSHOT!0.3!o' {} \;
