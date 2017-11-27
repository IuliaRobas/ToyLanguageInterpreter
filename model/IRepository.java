package model;


import model.PrgState;

import java.io.IOException;

public interface IRepository {
    PrgState getCrtPrg();
    public boolean add(PrgState state);

    //Save the content of the PrgState into a textFile
    //Throws: IOException - if the file exists but is a directory rather than a regular file,
    // does not exist but cannot be created,
    // or cannot be opened for any other reason
    public void logPrgStateExec() throws IOException;

}
