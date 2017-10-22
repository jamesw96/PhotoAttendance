import zipfile
zip_ref = zipfile.ZipFile("../uploads/STUDENTPHOTOS.zip", 'r')
zip_ref.extractall("../data/student/")
zip_ref.close()