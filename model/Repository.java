package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    List<PrgState> prgStates=new ArrayList<PrgState>();
    String logFilePath;
    PrgState prgState;

    @Override
    public PrgState getCrtPrg() {
        //return prgStates.get(prgStates.size()-1);
        return this.prgState;
    }

    public Repository(PrgState prgState,String logFilePath) {
        this.logFilePath = logFilePath;
        this.prgState = prgState;
    }

    @Override
    public boolean add(PrgState state) {
        return prgStates.add(state);
    }

    public Repository(){}

    public Repository(String logFilePath){
        this.logFilePath=logFilePath;
    };

    public Repository(List<PrgState> prgStates, String logFilePath) {
        this.prgStates = prgStates;
        this.logFilePath = logFilePath;
    }

    public Repository(List<PrgState> prgStates) {
        this.prgStates = prgStates;
    }

    public List<PrgState> getPrgStates() {
        return prgStates;
    }

    public void setPrgStates(List<PrgState> prgStates) {
        this.prgStates = prgStates;
    }

    public void logPrgStateExec() throws IOException{
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath,true)));
        logFile.write(this.prgState.toString());
        logFile.write("\n");
        logFile.close();
    }


}
