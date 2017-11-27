package model;

import exceptions.ExpressionException;
import exceptions.MyStackException;
import model.statements.IStmt;
import model.structures.MyIDictionary;
import model.structures.MyIStack;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {


    public Controller(IRepository repository) {
        this.repository = repository;
    }

    IRepository repository = new Repository();

    public PrgState oneStep(PrgState state) throws MyStackException, ExpressionException, IOException {
        MyIStack<IStmt> stk = state.getExeStack();
        try {
            IStmt crtStmt = stk.pop();
            return crtStmt.execute(state);
        } catch (MyStackException e) {
            throw e;
        } catch (ExpressionException ee) {
            throw ee;
        }
    }

    public void allSteps() throws MyStackException, ExpressionException, IOException {
        PrgState prg = repository.getCrtPrg();
        MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable = prg.getFileTable();

        try {
            while (true) {
                oneStep(prg);
                prg.getHeap().
                        setContent(conservativeGarbageCollector(prg.getSymTable().getContent().values(),
                                prg.getHeap().getContent()));
                repository.logPrgStateExec();
                System.out.println(prg);
            }
        } catch (MyStackException e) {
            throw e;
        } catch
                (ExpressionException ee) {
            throw ee;

        } catch (IOException ioe) {
            throw ioe;
        } finally {
            fileTable.values().stream().forEach(key ->
            {
                try {
                    fileTable.getContent().get(key).getItem2().close();
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());

                }
            });
        }
    }

    public Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTableValues,
                                                              Map<Integer, Integer> heap) {

        return heap.entrySet()
                .stream()
                .filter(e -> symTableValues.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        /* Map<Integer,Integer> result=new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : heap.entrySet()) {
            Integer key = entry.getKey();
            if(symTableValues.contains(key)==true)
               // heap.remove(key);

                result.put(key,entry.getValue());

        }
        return result;*/
    }


}
