package com.example.javafxdiplomawork;

import com.example.javafxdiplomawork.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ItemController {

    @FXML
    private AnchorPane bgPane;
    @FXML
    private Label fifthLabel;
    @FXML
    private Label firstLabel;
    @FXML
    private Label fourthLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label sixthLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label seventhLabel;
    @FXML
    private ImageView image;

    public void initialize() {

    }


    public void setData(CPU cpu) {
        firstLabel.setText(cpu.getName());
        secondLabel.setText("Price: "+cpu.getPrice()+"UAH");
        thirdLabel.setText(cpu.getThreads());
        fourthLabel.setText(cpu.getSocket());
        fifthLabel.setText(cpu.getCompatibilityMemory());
        sixthLabel.setText(cpu.getFrequency());
        seventhLabel.setText("TDP: " +cpu.getTDP()+"W");
        image.setImage(cpu.getImage());
    }

    public void setData(Motherboard mbrd) {
        firstLabel.setText(mbrd.getName());
        secondLabel.setText(mbrd.getChipset());
        thirdLabel.setText(mbrd.getFormFactor());
        fourthLabel.setText("Price: "+mbrd.getPrice()+"UAH");
        fifthLabel.setText(mbrd.getSocket());
        sixthLabel.setText(mbrd.getMemoryType());
        image.setImage(mbrd.getImage());
        seventhLabel.setVisible(false);
    }

    protected void setData(Cooling cooling) {
        firstLabel.setText(cooling.getName());
        secondLabel.setText("Price: "+cooling.getPrice()+"UAH");
        thirdLabel.setText("TDP: "+cooling.getTdp());
        fourthLabel.setText(cooling.getManufacturer());
        fifthLabel.setText(cooling.getType());
        image.setImage(cooling.getImage());
        sixthLabel.setVisible(false);
        seventhLabel.setVisible(false);
    }

    protected void setData(GPU gpu) {
        firstLabel.setText(gpu.getName());
        secondLabel.setText("Price: "+gpu.getPrice()+"UAH");
        thirdLabel.setText(gpu.getSeries());
        fourthLabel.setText(gpu.getManufacturer());
        fifthLabel.setText(gpu.getMemory_size());
        sixthLabel.setText("Watts: " +gpu.getWatts()+"W");
        image.setImage(gpu.getImage());
        seventhLabel.setVisible(false);
    }

    protected void setData(PowerSupply powerSupply) {
        firstLabel.setText(powerSupply.getName());
        secondLabel.setText("Price: "+powerSupply.getPrice()+"UAH");
        thirdLabel.setText(powerSupply.getManufacturerId());
        fourthLabel.setText("Power: " +powerSupply.getPower()+"W");
        fifthLabel.setText(powerSupply.getCertificate());
        image.setImage(powerSupply.getImage());
        sixthLabel.setVisible(false);
        seventhLabel.setVisible(false);
    }

    protected void setData(Memory memory) {
        firstLabel.setText(memory.getName());
        secondLabel.setText("Price: "+memory.getPrice()+"UAH");
        thirdLabel.setText(memory.getManufacturer());
        fourthLabel.setText(memory.getMemorySize());
        fifthLabel.setText(memory.getType());
        image.setImage(memory.getImage());
        sixthLabel.setVisible(false);
        seventhLabel.setVisible(false);
    }

    protected void setData(PCCase pcCase) {
        firstLabel.setText(pcCase.getName());
        secondLabel.setText("Price: "+pcCase.getPrice()+"UAH");
        thirdLabel.setText(pcCase.getManufacturer());
        fourthLabel.setText(pcCase.getFormFactor());
        image.setImage(pcCase.getImage());
        sixthLabel.setVisible(false);
        seventhLabel.setVisible(false);
        fifthLabel.setVisible(false);
    }

    protected void setData(Ram ram) {
        firstLabel.setText(ram.getName());
        secondLabel.setText("Price: "+ram.getPrice()+"UAH");
        thirdLabel.setText(ram.getManufacturer());
        fourthLabel.setText(ram.getKit());
        fifthLabel.setText(ram.getMemorySize());
        sixthLabel.setText(ram.getTypeMemory());
        seventhLabel.setVisible(false);
        image.setImage(ram.getImage());
    }

}
