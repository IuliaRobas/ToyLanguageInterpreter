package model;

import model.statements.IStmt;
import model.structures.MyIDictionary;
import model.structures.MyIHeap;
import model.structures.MyIList;
import model.structures.MyIStack;

import java.io.BufferedReader;

public class PrgState {

    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Integer> symTable;
    MyIList<Integer> out;
    IStmt originalProgram;
    MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable;
    MyIHeap<Integer> heap;

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable, IStmt originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;

        this.exeStack.push(originalProgram);
    }

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable, IStmt originalProgram, MyIHeap<Integer> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;

        this.exeStack.push(originalProgram);
    }

    @Override
    public String toString() {
        return "\n" + "***************PrgState***************" + "\n" +
                "ExeStack: " + exeStack + "\n" +
                "SymTable: " + symTable + "\n" +
                "Out: " + out + "\n" +
                "Heap: " + heap;
    }

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
    }

    public PrgState(MyIStack<IStmt> exeStack, MyIDictionary<String, Integer> symTable, MyIList<Integer> out, MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
    }

    public MyIHeap<Integer> getHeap() {
        return heap;
    }

    public void setHeap(MyIHeap<Integer> heap) {
        this.heap = heap;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Integer> symtbl,
                    MyIList<Integer> ot, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        originalProgram = prg;
        stk.push(prg);

    }

    public MyIStack<IStmt> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyIStack<IStmt> exeStack) {
        this.exeStack = exeStack;
    }

    public MyIDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(MyIDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public MyIList<Integer> getOut() {
        return out;
    }

    public void setOut(MyIList<Integer> out) {
        this.out = out;
    }

    public IStmt getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    public MyIDictionary<Integer, Tuple<String, BufferedReader>> getFileTable() {
        return fileTable;
    }

    public void setFileTable(MyIDictionary<Integer, Tuple<String, BufferedReader>> fileTable) {
        this.fileTable = fileTable;
    }
}


