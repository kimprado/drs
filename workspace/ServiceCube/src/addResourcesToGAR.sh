#!/bin/sh
rm temp_dir_AddResourcesToGAR/ -R
mkdir temp_dir_AddResourcesToGAR
rm d_org_cube_service_added.gar

unzip -d ./temp_dir_AddResourcesToGAR  org_cube_service.gar
$JAVA_HOME/bin/jar uf temp_dir_AddResourcesToGAR/lib/org_cube_service.jar META-INF/persistence.xml

#pwd

cd ./temp_dir_AddResourcesToGAR

#pwd

zip -r d_org_cube_service_added.gar * 

cp d_org_cube_service_added.gar ../d_org_cube_service_added.gar

cd ../

rm temp_dir_AddResourcesToGAR/ -R
