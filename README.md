# family-geektrust
Instructions to run the application: 
   ->  Unzip the folder
   ->  This project uses gradle as the build system. Build the jar with command ( gradle clean build )
   ->  Once build is successful, there will be a jar file named geektrust.jar in the build/libs folder
   ->  Run the jar with the command java -jar $PATH_TO_TEST_FILE. Path to the test file can be relative from the current folder user is in.

Key points: 
   -> Have assumed no two people in the family have the same name. Have used name parameter to uniquely identify any person in the tree.
   -> Have used a Hash Map to store family members names and the corresponding attributes. 
      - This provides for faster lookup and as the mandatory operations do not require extensive traversal along the family tree, we have modelled the 'Person' class to contain information that makes it possible to easily retrieve any family relation within two levels of heirarchy. 
   -> Have created singleton objects for classes where no temporary state information is stored so as to not create any unnecessary objects.