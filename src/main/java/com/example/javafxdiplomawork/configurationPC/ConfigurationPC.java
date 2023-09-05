package com.example.javafxdiplomawork.configurationPC;

import com.example.javafxdiplomawork.model.*;

public class ConfigurationPC {
    private CPU cpu;
    private GPU gpu;
    private Cooling cooling;
    private Memory memory;
    private Motherboard motherboard;
    private PCCase casePC;
    private Ram ram;
    private PowerSupply powerSupply;

    public PowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PowerSupply powerSupply) {
        this.powerSupply = powerSupply;
    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public void setGpu(GPU gpu) {
        this.gpu = gpu;
    }

    public Cooling getCooling() {
        return cooling;
    }

    public void setCooling(Cooling cooling) {
        this.cooling = cooling;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public Motherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(Motherboard motherboard) {
        this.motherboard = motherboard;
    }

    public PCCase getCasePC() {
        return casePC;
    }

    public void setCasePC(PCCase casePC) {
        this.casePC = casePC;
    }

    public Ram getRam() {
        return ram;
    }

    public void setRam(Ram ram) {
        this.ram = ram;
    }

    public boolean checkCPU(){
        if(cpu.equals(null)){
            return false;
        }else{
            return true;
        }
    }
}
