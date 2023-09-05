package com.example.javafxdiplomawork;

import com.example.javafxdiplomawork.configurationPC.ConfigurationPC;
import com.example.javafxdiplomawork.database.DatabaseHandler;
import com.example.javafxdiplomawork.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static javafx.scene.layout.GridPane.setMargin;
import static org.codehaus.plexus.util.StringUtils.trim;

public class MainController implements Initializable {
    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;
    @FXML
    private Circle c4;
    @FXML
    private Circle c5;
    @FXML
    private Circle c6;
    @FXML
    private Circle c7;
    @FXML
    private Circle c8;
    @FXML
    private Label orderLabel1;
    @FXML
    private Label orderLabel2;
    @FXML
    private Label orderLabel3;
    @FXML
    private Button saveBuildButton;
    @FXML
    private RadioButton ascending;
    @FXML
    private RadioButton descending;
    @FXML
    private Label totalPrice;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private Button caseButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button coolingButton;
    @FXML
    private Button cpuButton;
    @FXML
    private Button gpuButton;
    @FXML
    private Button hddButton;
    @FXML
    private Button motherboardButton;
    @FXML
    private Button powerSupplyButton;
    @FXML
    private Button ramButton;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView currentItem;
    @FXML
    private Label currentLabel1;
    @FXML
    private Label currentLabel2;
    @FXML
    private Label currentLabel3;
    @FXML
    private Label currentLabel4;
    @FXML
    private Label currentLabel5;
    @FXML
    private Label currentLabel6;
    @FXML
    private Label priceLabel1;
    @FXML
    private Label priceLabel2;
    @FXML
    private Label priceLabel3;
    private List<CPU> cpu = new ArrayList<>();
    private List<Motherboard> motherboard = new ArrayList<>();
    private List<Cooling> cooling = new ArrayList<>();
    private List<PCCase> pcCase = new ArrayList<>();
    private List<PowerSupply> powerSupply = new ArrayList<>();
    private List<Ram> ram = new ArrayList<>();
    private List<Memory> memory = new ArrayList<>();
    private List<GPU> gpu = new ArrayList<>();
    private final List<String> cpuName = new ArrayList<>();
    private final List<String> gpuName = new ArrayList<>();
    private static final List<String> motherboardName = new ArrayList<>();
    private final List<String> coolingName = new ArrayList<>();
    private final List<String> ramName = new ArrayList<>();
    private final List<String> memoryName = new ArrayList<>();
    private final List<String> powerSupplyName = new ArrayList<>();
    private final List<String> caseName = new ArrayList<>();
    static String tableName = "CPU";
    ConfigurationPC configurationPC = new ConfigurationPC();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        saveBuildButton.setOnAction(event -> {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            try {
                int sum = totalPrice();
                databaseHandler.createOrder(User.getId(), configurationPC, sum);
                showOrderList();
                hideAllCurrentItemInfo();
                configurationPC.setCpu(null);
                configurationPC.setGpu(null);
                configurationPC.setCooling(null);
                configurationPC.setMotherboard(null);
                configurationPC.setPowerSupply(null);
                configurationPC.setCasePC(null);
                configurationPC.setRam(null);
                configurationPC.setMotherboard(null);
                saveBuildButton.setVisible(false);
                totalPriceLabel.setVisible(false);
                totalPrice.setVisible(false);
                c1.setFill(Color.rgb(197, 197, 197));
                c2.setFill(Color.rgb(197, 197, 197));
                c3.setFill(Color.rgb(197, 197, 197));
                c4.setFill(Color.rgb(197, 197, 197));
                c5.setFill(Color.rgb(197, 197, 197));
                c6.setFill(Color.rgb(197, 197, 197));
                c7.setFill(Color.rgb(197, 197, 197));
                c8.setFill(Color.rgb(197, 197, 197));
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        hideAllCurrentItemInfo();
        ascending.setOnAction(event -> {
            if (ascending.isSelected() && Objects.equals(tableName, "CPU")) {
                for (int i = 0; i < cpu.size(); i++) {
                    for (int j = 0; j < cpu.size() - 1; j++) {
                        if (Integer.parseInt(cpu.get(j).getPrice()) > Integer.parseInt(cpu.get(j + 1).getPrice())) {
                            CPU tmp = cpu.get(j);
                            cpu.set(j, cpu.get(j + 1));
                            cpu.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCPU();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "motherboard")) {
                for (int i = 0; i < motherboard.size(); i++) {
                    for (int j = 0; j < motherboard.size() - 1; j++) {
                        if (Integer.parseInt(motherboard.get(j).getPrice()) > Integer.parseInt(motherboard.get(j + 1).getPrice())) {
                            Motherboard tmp = motherboard.get(j);
                            motherboard.set(j, motherboard.get(j + 1));
                            motherboard.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemMotherboard();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "GPU")) {
                for (int i = 0; i < gpu.size(); i++) {
                    for (int j = 0; j < gpu.size() - 1; j++) {
                        if (Integer.parseInt(gpu.get(j).getPrice()) > Integer.parseInt(gpu.get(j + 1).getPrice())) {
                            GPU tmp = gpu.get(j);
                            gpu.set(j, gpu.get(j + 1));
                            gpu.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemGPU();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "cooling")) {
                for (int i = 0; i < cooling.size(); i++) {
                    for (int j = 0; j < cooling.size() - 1; j++) {
                        if (Integer.parseInt(cooling.get(j).getPrice()) > Integer.parseInt(cooling.get(j + 1).getPrice())) {
                            Cooling tmp = cooling.get(j);
                            cooling.set(j, cooling.get(j + 1));
                            cooling.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCooling();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "hdd/ssd")) {
                for (int i = 0; i < memory.size(); i++) {
                    for (int j = 0; j < memory.size() - 1; j++) {
                        if (Integer.parseInt(memory.get(j).getPrice()) > Integer.parseInt(memory.get(j + 1).getPrice())) {
                            Memory tmp = memory.get(j);
                            memory.set(j, memory.get(j + 1));
                            memory.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemMemory();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ascending.isSelected() && Objects.equals(tableName, "pc_case")) {
                for (int i = 0; i < pcCase.size(); i++) {
                    for (int j = 0; j < pcCase.size() - 1; j++) {
                        if (Integer.parseInt(pcCase.get(j).getPrice()) > Integer.parseInt(pcCase.get(j + 1).getPrice())) {
                            PCCase tmp = pcCase.get(j);
                            pcCase.set(j, pcCase.get(j + 1));
                            pcCase.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCase();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "power_supply")) {
                for (int i = 0; i < powerSupply.size(); i++) {
                    for (int j = 0; j < powerSupply.size() - 1; j++) {
                        if (Integer.parseInt(powerSupply.get(j).getPrice()) > Integer.parseInt(powerSupply.get(j + 1).getPrice())) {
                            PowerSupply tmp = powerSupply.get(j);
                            powerSupply.set(j, powerSupply.get(j + 1));
                            powerSupply.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemPowerSupply();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (ascending.isSelected() && Objects.equals(tableName, "ram")) {
                for (int i = 0; i < ram.size(); i++) {
                    for (int j = 0; j < ram.size() - 1; j++) {
                        if (Integer.parseInt(ram.get(j).getPrice()) > Integer.parseInt(ram.get(j + 1).getPrice())) {
                            Ram tmp = ram.get(j);
                            ram.set(j, ram.get(j + 1));
                            ram.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemRam();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

        });
        descending.setOnAction(event -> {
            if (descending.isSelected() && Objects.equals(tableName, "GPU")) {
                for (int i = 0; i < gpu.size(); i++) {
                    for (int j = 0; j < gpu.size() - 1; j++) {
                        if (Integer.parseInt(gpu.get(j).getPrice()) < Integer.parseInt(gpu.get(j + 1).getPrice())) {
                            GPU tmp = gpu.get(j);
                            gpu.set(j, gpu.get(j + 1));
                            gpu.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemGPU();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "CPU")) {
                for (int i = 0; i < cpu.size(); i++) {
                    for (int j = 0; j < cpu.size() - 1; j++) {
                        if (Integer.parseInt(cpu.get(j).getPrice()) < Integer.parseInt(cpu.get(j + 1).getPrice())) {
                            CPU tmp = cpu.get(j);
                            cpu.set(j, cpu.get(j + 1));
                            cpu.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCPU();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "motherboard")) {
                for (int i = 0; i < motherboard.size(); i++) {
                    for (int j = 0; j < motherboard.size() - 1; j++) {
                        if (Integer.parseInt(motherboard.get(j).getPrice()) < Integer.parseInt(motherboard.get(j + 1).getPrice())) {
                            Motherboard tmp = motherboard.get(j);
                            motherboard.set(j, motherboard.get(j + 1));
                            motherboard.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemMotherboard();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "cooling")) {
                for (int i = 0; i < cooling.size(); i++) {
                    for (int j = 0; j < cooling.size() - 1; j++) {
                        if (Integer.parseInt(cooling.get(j).getPrice()) < Integer.parseInt(cooling.get(j + 1).getPrice())) {
                            Cooling tmp = cooling.get(j);
                            cooling.set(j, cooling.get(j + 1));
                            cooling.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCooling();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "hdd/ssd")) {
                for (int i = 0; i < memory.size(); i++) {
                    for (int j = 0; j < memory.size() - 1; j++) {
                        if (Integer.parseInt(memory.get(j).getPrice()) < Integer.parseInt(memory.get(j + 1).getPrice())) {
                            Memory tmp = memory.get(j);
                            memory.set(j, memory.get(j + 1));
                            memory.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemMemory();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "power_supply")) {
                for (int i = 0; i < powerSupply.size(); i++) {
                    for (int j = 0; j < powerSupply.size() - 1; j++) {
                        if (Integer.parseInt(powerSupply.get(j).getPrice()) < Integer.parseInt(powerSupply.get(j + 1).getPrice())) {
                            PowerSupply tmp = powerSupply.get(j);
                            powerSupply.set(j, powerSupply.get(j + 1));
                            powerSupply.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemPowerSupply();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "pc_case")) {
                for (int i = 0; i < pcCase.size(); i++) {
                    for (int j = 0; j < pcCase.size() - 1; j++) {
                        if (Integer.parseInt(pcCase.get(j).getPrice()) < Integer.parseInt(pcCase.get(j + 1).getPrice())) {
                            PCCase tmp = pcCase.get(j);
                            pcCase.set(j, pcCase.get(j + 1));
                            pcCase.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemCase();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            if (descending.isSelected() && Objects.equals(tableName, "ram")) {
                for (int i = 0; i < ram.size(); i++) {
                    for (int j = 0; j < ram.size() - 1; j++) {
                        if (Integer.parseInt(ram.get(j).getPrice()) < Integer.parseInt(ram.get(j + 1).getPrice())) {
                            Ram tmp = ram.get(j);
                            ram.set(j, ram.get(j + 1));
                            ram.set(j + 1, tmp);
                        }
                    }
                }
                try {
                    loadItemRam();
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        cpuButton.setOnAction(event -> {
            if (!Objects.equals(tableName, "CPU")) {
                tableName = "CPU";
                try {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemCPU();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getCpu() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getCpu().getImage());
                        currentLabel1.setText(configurationPC.getCpu().getName());
                        currentLabel2.setText("Price: " + (configurationPC.getCpu().getPrice()) + "UAH");
                        currentLabel3.setText(String.valueOf(configurationPC.getCpu().getFrequency()));
                        currentLabel4.setText(String.valueOf(configurationPC.getCpu().getThreads()));
                        currentLabel5.setText(("TDP: " + (configurationPC.getCpu().getTDP() + "W")));
                        currentLabel6.setText(String.valueOf(configurationPC.getCpu().getCompatibilityMemory()));

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        motherboardButton.setOnAction(event -> {
            if (!Objects.equals(tableName, "motherboard"))
                tableName = "motherboard";
            try {
                if (configurationPC.getCpu() == null) {
                    System.out.println("select CPU");
                } else if (configurationPC.getCpu() != null) {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemMotherboard();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getMotherboard() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getMotherboard().getImage());
                        currentLabel1.setText(String.valueOf(configurationPC.getMotherboard().getName()));
                        currentLabel2.setText("Price: " + configurationPC.getMotherboard().getPrice() + "UAH");
                        currentLabel3.setText(String.valueOf(configurationPC.getMotherboard().getChipset()));
                        currentLabel4.setText(String.valueOf(configurationPC.getMotherboard().getFormFactor()));
                        currentLabel5.setText(String.valueOf(configurationPC.getMotherboard().getManufacturer()));
                        currentLabel6.setText(String.valueOf(configurationPC.getMotherboard().getMemoryType()));
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        caseButton.setOnAction(event -> {
            tableName = "pc_case";
            try {
                if (configurationPC.getMotherboard() == null) {
                    System.out.println("select motherboard");
                } else {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemCase();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getCasePC() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getCasePC().getImage());
                        currentLabel1.setText(configurationPC.getCasePC().getName());
                        currentLabel2.setText("Price: " + (configurationPC.getCasePC().getPrice()) + "UAH");
                        currentLabel3.setText(configurationPC.getCasePC().getManufacturer());
                        currentLabel4.setText(configurationPC.getCasePC().getFormFactor());
                        currentLabel5.setVisible(false);
                        currentLabel6.setVisible(false);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        ramButton.setOnAction(event -> {
            tableName = "ram";
            try {
                if (configurationPC.getCpu() == null || configurationPC.getMotherboard() == null) {
                    System.out.println("select cpu->motherboard");
                } else {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemRam();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getRam() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getRam().getImage());
                        currentLabel1.setText(configurationPC.getRam().getName());
                        currentLabel2.setText("Price: " + (configurationPC.getRam().getPrice()) + "UAH");
                        currentLabel3.setText(configurationPC.getRam().getManufacturer());
                        currentLabel4.setText(configurationPC.getRam().getMemorySize());
                        currentLabel5.setText(configurationPC.getRam().getTypeMemory());
                        currentLabel6.setText(configurationPC.getRam().getKit());
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        hddButton.setOnAction(event -> {
            tableName = "hdd/ssd";
            try {
                ascending.setSelected(false);
                descending.setSelected(false);
                loadItemMemory();
                hideAllCurrentItemInfo();
                if (configurationPC.getMemory() != null) {
                    showAllLabels();
                    currentItem.setImage(configurationPC.getMemory().getImage());
                    currentLabel1.setText(configurationPC.getMemory().getName());
                    currentLabel2.setText("Price: " + (configurationPC.getMemory().getPrice()) + "UAH");
                    currentLabel3.setText(configurationPC.getMemory().getManufacturer());
                    currentLabel4.setText(configurationPC.getMemory().getMemorySize());
                    currentLabel5.setVisible(false);
                    currentLabel6.setVisible(false);
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        powerSupplyButton.setOnAction(event -> {
            tableName = "power_supply";
            try {
                if (configurationPC.getCpu() == null || configurationPC.getGpu() == null) {
                    System.out.println("select cpu->gpu");
                } else {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemPowerSupply();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getPowerSupply() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getPowerSupply().getImage());
                        currentLabel1.setText(configurationPC.getPowerSupply().getName());
                        currentLabel2.setText("Price: " + (configurationPC.getPowerSupply().getPrice()) + "UAH");
                        currentLabel3.setText(configurationPC.getPowerSupply().getManufacturerId());
                        currentLabel4.setText(configurationPC.getPowerSupply().getCertificate());
                        currentLabel5.setText("Power: " + (configurationPC.getPowerSupply().getPower() + "W"));
                        currentLabel6.setVisible(false);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        gpuButton.setOnAction(event -> {
            tableName = "GPU";
            try {
                ascending.setSelected(false);
                descending.setSelected(false);
                loadItemGPU();
                hideAllCurrentItemInfo();
                if (configurationPC.getGpu() != null) {
                    showAllLabels();
                    currentItem.setImage(configurationPC.getGpu().getImage());
                    currentLabel1.setText(String.valueOf(configurationPC.getGpu().getName()));
                    currentLabel2.setText("Price: " + (configurationPC.getGpu().getPrice()) + "UAH");
                    currentLabel3.setText(String.valueOf(configurationPC.getGpu().getManufacturer()));
                    currentLabel4.setText(String.valueOf(configurationPC.getGpu().getMemory_size()));
                    currentLabel5.setText(String.valueOf(configurationPC.getGpu().getSeries()));
                    currentLabel6.setText(("Watts: " + configurationPC.getGpu().getWatts() + "W"));
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        coolingButton.setOnAction(event -> {
            tableName = "cooling";
            try {
                if (configurationPC.getCpu() == null) {
                    System.out.println("select cpu");
                } else {
                    ascending.setSelected(false);
                    descending.setSelected(false);
                    loadItemCooling();
                    hideAllCurrentItemInfo();
                    if (configurationPC.getCooling() != null) {
                        showAllLabels();
                        currentItem.setImage(configurationPC.getCooling().getImage());
                        currentLabel1.setText(configurationPC.getCooling().getName());
                        currentLabel2.setText("Price: " + (configurationPC.getCooling().getPrice()) + "UAH");
                        currentLabel3.setText(configurationPC.getCooling().getManufacturer());
                        currentLabel4.setText("TDP: " + (configurationPC.getCooling().getTdp()));
                        currentLabel5.setText(configurationPC.getCooling().getType());
                        currentLabel6.setVisible(false);
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        closeButton.setOnAction(event -> closeButton.getScene().getWindow().hide());

        //loading of components
        try {
            pcCase = new ArrayList<>(dataCase());
            memory = new ArrayList<>(dataMemory());
            ram = new ArrayList<>(dataRam());
            powerSupply = new ArrayList<>(dataPowerSupply());
            gpu = new ArrayList<>(dataGPU());
            cooling = new ArrayList<>(dataCooling());
            motherboard = new ArrayList<>(dataMotherboard());
            cpu = new ArrayList<>(dataCPU());
            loadItemCPU();
            showOrderList();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    private void hideAllCurrentItemInfo() {
        currentItem.setVisible(false);
        currentLabel1.setVisible(false);
        currentLabel2.setVisible(false);
        currentLabel3.setVisible(false);
        currentLabel4.setVisible(false);
        currentLabel6.setVisible(false);
        currentLabel5.setVisible(false);
    }
    private void loadItemCase() throws SQLException, ClassNotFoundException {
        if (pcCase.isEmpty()) {
            pcCase = new ArrayList<>(dataCase());
        }
        int column = 0;
        int row = 0;
        try {
            int index = 0;
            grid.getChildren().clear();
            for (PCCase aCase : pcCase) {
                if (aCase.getIdFormFactor() >= configurationPC.getMotherboard().getIdFormFactor()) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane Item;
                    Item = fxmlLoader.load();
                    ItemController ItemController = fxmlLoader.getController();
                    ItemController.setData(aCase);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    Button temp = new Button();
                    temp.setId(String.valueOf(index));
                    temp.setPrefSize(370, 180);
                    temp.setStyle("-fx-background-color :  transparent");
                    temp.setOnAction(event -> {
                        showAllLabels();
                        c8.setFill(Color.rgb(255, 255, 255));
                        configurationPC.setCasePC(pcCase.get(Integer.parseInt(temp.getId())));
                        currentItem.setImage(pcCase.get(Integer.parseInt(temp.getId())).getImage());
                        currentLabel1.setText(String.valueOf(pcCase.get(Integer.parseInt(temp.getId())).getName()));
                        currentLabel2.setText("Price: " + (pcCase.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                        currentLabel3.setText(String.valueOf(pcCase.get(Integer.parseInt(temp.getId())).getManufacturer()));
                        currentLabel4.setText(String.valueOf(pcCase.get(Integer.parseInt(temp.getId())).getFormFactor()));
                        currentLabel5.setVisible(false);
                        currentLabel6.setVisible(false);
                        totalPrice();
                        try {
                            saveBuildPC();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    grid.add(Item, column, row);
                    grid.add(temp, column++, row);
                    setMargin(Item, new Insets(7));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllLabels() {
        currentItem.setVisible(true);
        currentLabel5.setVisible(true);
        currentLabel6.setVisible(true);
        currentLabel1.setVisible(true);
        currentLabel2.setVisible(true);
        currentLabel3.setVisible(true);
        currentLabel4.setVisible(true);
    }

    private ArrayList<PCCase> dataCase() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.pc_case join manufacturer ON  pc_case.manufacturer_id = manufacturer.id_manufacturer join form_factor " +
                "ON pc_case.form_factor = form_factor.id_form_factor");
        ArrayList<PCCase> list = new ArrayList<>();
        while (resultSet.next()) {
            PCCase pcCase = new PCCase();
            caseName.add(resultSet.getString("name"));
            pcCase.setId(resultSet.getString("id"));
            pcCase.setName(resultSet.getString("name"));
            pcCase.setPrice(resultSet.getString("price"));
            pcCase.setManufacturer("Manufacturer: " + resultSet.getString("manufacturer.name_manufacturer"));
            pcCase.setFormFactor("Form-factor : " + resultSet.getString("form_factor.name_form_factor"));
            pcCase.setIdFormFactor(Integer.parseInt(resultSet.getString("form_factor")));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            pcCase.setImage(new Image(is));
            list.add(pcCase);
        }
        return list;
    }

    private void loadItemMemory() throws SQLException, ClassNotFoundException {
        if (memory.isEmpty()) {
            memory = new ArrayList<>(dataMemory());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (Memory value : memory) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane Item;
                Item = fxmlLoader.load();
                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(value);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                Button temp = new Button();
                temp.setId(String.valueOf(index++));
                temp.setPrefSize(370, 180);
                temp.setStyle("-fx-background-color :  transparent");
                temp.setOnAction(event -> {
                    showAllLabels();
                    c6.setFill(Color.rgb(255, 255, 255));
                    configurationPC.setMemory(memory.get(Integer.parseInt(temp.getId())));
                    currentItem.setImage(memory.get(Integer.parseInt(temp.getId())).getImage());
                    currentLabel1.setText(String.valueOf(memory.get(Integer.parseInt(temp.getId())).getName()));
                    currentLabel2.setText("Price: " + (memory.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                    currentLabel3.setText(String.valueOf(memory.get(Integer.parseInt(temp.getId())).getManufacturer()));
                    currentLabel4.setText(String.valueOf(memory.get(Integer.parseInt(temp.getId())).getMemorySize()));
                    currentLabel5.setVisible(false);
                    currentLabel6.setVisible(false);
                    totalPrice();
                    try {
                        saveBuildPC();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                grid.add(Item, column, row);
                grid.add(temp, column++, row);
                setMargin(Item, new Insets(7));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ArrayList<Memory> dataMemory() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.memory join manufacturer ON  memory.manufacturer_id = manufacturer.id_manufacturer");
        ArrayList<Memory> list = new ArrayList<>();
        while (resultSet.next()) {
            Memory memory = new Memory();
            memoryName.add(resultSet.getString("name"));
            memory.setId(resultSet.getString("id"));
            memory.setName(resultSet.getString("name"));
            memory.setPrice(resultSet.getString("price"));
            memory.setManufacturer("Manufacturer: " + resultSet.getString("manufacturer.name_manufacturer"));
            memory.setType("Type : " + resultSet.getString("type"));
            memory.setMemorySize("Size : " + resultSet.getString("memory_size"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            memory.setImage(new Image(is));
            list.add(memory);
        }
        return list;
    }

    private void loadItemRam() throws SQLException, ClassNotFoundException {
        if (ram.isEmpty()) {
            ram = new ArrayList<>(dataRam());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (Ram value : ram) {
                if (Integer.parseInt(value.getFrequency()) <= Integer.parseInt(configurationPC.getMotherboard().getMaxMemoryFrequency())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane Item;
                    Item = fxmlLoader.load();
                    ItemController ItemController = fxmlLoader.getController();
                    ItemController.setData(value);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    Button temp = new Button();
                    temp.setId(String.valueOf(index));
                    temp.setPrefSize(370, 180);
                    temp.setStyle("-fx-background-color :  transparent");

                    temp.setOnAction(event -> {
                        showAllLabels();
                        c5.setFill(Color.rgb(255, 255, 255));
                        configurationPC.setRam(ram.get(Integer.parseInt(temp.getId())));
                        currentItem.setImage(ram.get(Integer.parseInt(temp.getId())).getImage());
                        currentLabel1.setText(String.valueOf(ram.get(Integer.parseInt(temp.getId())).getName()));
                        currentLabel2.setText("Price: " + (ram.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                        currentLabel3.setText(String.valueOf(ram.get(Integer.parseInt(temp.getId())).getManufacturer()));
                        currentLabel4.setText(String.valueOf(ram.get(Integer.parseInt(temp.getId())).getMemorySize()));
                        currentLabel5.setText(String.valueOf(ram.get(Integer.parseInt(temp.getId())).getTypeMemory()));
                        currentLabel6.setText(String.valueOf(ram.get(Integer.parseInt(temp.getId())).getKit()));
                        totalPrice();
                        try {
                            saveBuildPC();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });

                    grid.add(Item, column, row);
                    grid.add(temp, column++, row);
                    setMargin(Item, new Insets(7));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Ram> dataRam() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.ram join manufacturer ON  ram.manufacturer_id = manufacturer.id_manufacturer");
        ArrayList<Ram> list = new ArrayList<>();
        while (resultSet.next()) {
            Ram ram = new Ram();
            ramName.add(resultSet.getString("name"));
            ram.setId(resultSet.getString("id"));
            ram.setName(resultSet.getString("name"));
            ram.setPrice(resultSet.getString("price"));
            ram.setFrequency(resultSet.getString("frequency"));
            ram.setManufacturer("Manufacturer: " + resultSet.getString("manufacturer.name_manufacturer"));
            ram.setKit("Kit: " + resultSet.getString("kit"));
            ram.setTypeMemory("Type: " + resultSet.getString("type_memory") + "-" + resultSet.getString("frequency"));
            ram.setMemorySize("Size: " + resultSet.getString("memory_size") + "GB");
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            ram.setImage(new Image(is));
            list.add(ram);
        }
        return list;
    }

    private void loadItemPowerSupply() throws SQLException, ClassNotFoundException {
        if (powerSupply.isEmpty()) {
            powerSupply = new ArrayList<>(dataPowerSupply());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (PowerSupply supply : powerSupply) {

                int cpu_tdp = Integer.parseInt(configurationPC.getCpu().getTDP());
                int gpu_tdp = Integer.parseInt(configurationPC.getGpu().getWatts());
                if (Integer.parseInt(supply.getPower()) >= cpu_tdp + gpu_tdp + 150) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane Item;
                    Item = fxmlLoader.load();
                    ItemController ItemController = fxmlLoader.getController();
                    ItemController.setData(supply);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    Button temp = new Button();
                    temp.setId(String.valueOf(index));
                    temp.setPrefSize(370, 180);
                    temp.setStyle("-fx-background-color :  transparent");
                    temp.setOnAction(event -> {
                        showAllLabels();
                        c7.setFill(Color.rgb(255, 255, 255));
                        configurationPC.setPowerSupply(powerSupply.get(Integer.parseInt(temp.getId())));
                        currentItem.setImage(powerSupply.get(Integer.parseInt(temp.getId())).getImage());
                        currentLabel1.setText(String.valueOf(powerSupply.get(Integer.parseInt(temp.getId())).getName()));
                        currentLabel2.setText("Price: " + (powerSupply.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                        currentLabel3.setText(String.valueOf(powerSupply.get(Integer.parseInt(temp.getId())).getManufacturerId()));
                        currentLabel4.setText(String.valueOf(powerSupply.get(Integer.parseInt(temp.getId())).getCertificate()));
                        currentLabel5.setText("Power: " + (powerSupply.get(Integer.parseInt(temp.getId())).getPower() + "W"));
                        currentLabel6.setVisible(false);
                        totalPrice();
                        try {
                            saveBuildPC();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    grid.add(Item, column, row);
                    grid.add(temp, column++, row);
                    setMargin(Item, new Insets(7));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<PowerSupply> dataPowerSupply() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.power_supply join manufacturer ON  power_supply.manufacturer_id = manufacturer.id_manufacturer");
        ArrayList<PowerSupply> list = new ArrayList<>();
        while (resultSet.next()) {
            powerSupplyName.add(resultSet.getString("name"));
            PowerSupply powerSupply = new PowerSupply();
            powerSupply.setId(resultSet.getString("id"));
            powerSupply.setName(resultSet.getString("name"));
            powerSupply.setPrice(resultSet.getString("price"));
            powerSupply.setManufacturer("Manufacturer: " + resultSet.getString("manufacturer.name_manufacturer"));
            powerSupply.setPower(trim(resultSet.getString("power")));
            powerSupply.setCertificate("Certificate: 80+" + resultSet.getString("certificate"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            powerSupply.setImage(new Image(is));
            list.add(powerSupply);
        }
        return list;
    }

    private void loadItemGPU() throws SQLException, ClassNotFoundException {
        if (gpu.isEmpty()) {
            gpu = new ArrayList<>(dataGPU());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (GPU value : gpu) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane Item;
                Item = fxmlLoader.load();
                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(value);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                Button temp = new Button();
                temp.setId(String.valueOf(index++));
                temp.setPrefSize(370, 180);
                temp.setStyle("-fx-background-color :  transparent");

                temp.setOnAction(event -> {
                    showAllLabels();
                    c4.setFill(Color.rgb(255, 255, 255));
                    configurationPC.setGpu(gpu.get(Integer.parseInt(temp.getId())));
                    currentItem.setImage(gpu.get(Integer.parseInt(temp.getId())).getImage());
                    currentLabel1.setText(String.valueOf(gpu.get(Integer.parseInt(temp.getId())).getName()));
                    currentLabel2.setText("Price: " + (gpu.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                    currentLabel3.setText(String.valueOf(gpu.get(Integer.parseInt(temp.getId())).getManufacturer()));
                    currentLabel4.setText(String.valueOf(gpu.get(Integer.parseInt(temp.getId())).getMemory_size()));
                    currentLabel5.setText(String.valueOf(gpu.get(Integer.parseInt(temp.getId())).getSeries()));
                    currentLabel6.setText(("Watts: " + gpu.get(Integer.parseInt(temp.getId())).getWatts() + "W"));

                    totalPrice();
                    try {
                        saveBuildPC();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    configurationPC.setPowerSupply(null);
                });
                grid.add(Item, column, row);
                grid.add(temp, column++, row);
                setMargin(Item, new Insets(7));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<GPU> dataGPU() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.GPU join manufacturer ON  GPU.manufacturer_id = manufacturer.id_manufacturer");
        ArrayList<GPU> list = new ArrayList<>();
        while (resultSet.next()) {
            gpuName.add(resultSet.getString("name"));
            GPU gpu = new GPU();
            gpu.setId(resultSet.getString("id"));
            gpu.setName(resultSet.getString("name"));
            gpu.setPrice(resultSet.getString("price"));
            gpu.setWatts(resultSet.getString("watts"));
            gpu.setManufacturer("Manufacturer: " + resultSet.getString("manufacturer.name_manufacturer"));
            gpu.setMemory_size("Memory: " + resultSet.getString("memory_size") + "GB");
            gpu.setSeries("Series: " + resultSet.getString("series"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            gpu.setImage(new Image(is));
            list.add(gpu);
        }
        return list;
    }

    private void loadItemCooling() throws SQLException, ClassNotFoundException {
        if (cooling.isEmpty()) {
            cooling = new ArrayList<>(dataCooling());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (Cooling value : cooling) {
                String cpuTDP = configurationPC.getCpu().getTDP();
                String coolingTDP = value.getTdp();
                if (Integer.parseInt(cpuTDP) <= Integer.parseInt(coolingTDP)) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane Item;
                    Item = fxmlLoader.load();
                    ItemController ItemController = fxmlLoader.getController();
                    ItemController.setData(value);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    Button temp = new Button();
                    temp.setId(String.valueOf(index));
                    temp.setPrefSize(370, 180);
                    temp.setStyle("-fx-background-color :  transparent");
                    temp.setOnAction(event -> {
                        showAllLabels();
                        c3.setFill(Color.rgb(255, 255, 255));
                        configurationPC.setCooling(cooling.get(Integer.parseInt(temp.getId())));
                        currentItem.setImage(cooling.get(Integer.parseInt(temp.getId())).getImage());
                        currentLabel1.setText(String.valueOf(cooling.get(Integer.parseInt(temp.getId())).getName()));
                        currentLabel2.setText("Price: " + (cooling.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                        currentLabel3.setText(String.valueOf(cooling.get(Integer.parseInt(temp.getId())).getManufacturer()));
                        currentLabel4.setText("TDP: " + (cooling.get(Integer.parseInt(temp.getId())).getTdp()));
                        currentLabel5.setText(String.valueOf(cooling.get(Integer.parseInt(temp.getId())).getType()));
                        currentLabel6.setVisible(false);
                        totalPrice();
                        try {
                            saveBuildPC();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    grid.add(Item, column, row);
                    grid.add(temp, column++, row);
                    setMargin(Item, new Insets(7));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Cooling> dataCooling() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.cooling join manufacturer ON  cooling.manufacturer_id = manufacturer.id_manufacturer");
        List<Cooling> list = new ArrayList<>();
        while (resultSet.next()) {
            coolingName.add(resultSet.getString("name"));
            Cooling cooling = new Cooling();
            cooling.setId(resultSet.getString("id"));
            cooling.setName(resultSet.getString("name"));
            cooling.setPrice(resultSet.getString("price"));
            cooling.setType("Type: " + resultSet.getString("type"));
            cooling.setTdp(resultSet.getString("tdp"));
            cooling.setManufacturer("Manufacturer:" + resultSet.getString("manufacturer.name_manufacturer"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            cooling.setImage(new Image(is));
            list.add(cooling);
        }
        return list;
    }

    private void loadItemMotherboard() throws SQLException, ClassNotFoundException {
        if (motherboard.isEmpty()) {
            motherboard = new ArrayList<>(dataMotherboard());
        }
        int column = 0;
        int row = 0;
        try {
            grid.getChildren().clear();
            int index = 0;
            for (Motherboard value : motherboard) {
                if (configurationPC.getCpu().getSocket().equals(value.getSocket())) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                    AnchorPane Item;
                    Item = fxmlLoader.load();
                    ItemController ItemController = fxmlLoader.getController();
                    ItemController.setData(value);
                    if (column == 2) {
                        column = 0;
                        row++;
                    }
                    Button temp = new Button();
                    temp.setId(String.valueOf(index));
                    temp.setPrefSize(370, 180);
                    temp.setStyle("-fx-background-color :  transparent");
                    temp.setOnAction(event -> {
                        showAllLabels();
                        c2.setFill(Color.rgb(255, 255, 255));
                        configurationPC.setMotherboard(motherboard.get(Integer.parseInt(temp.getId())));
                        currentItem.setImage(motherboard.get(Integer.parseInt(temp.getId())).getImage());
                        currentLabel1.setText(String.valueOf(motherboard.get(Integer.parseInt(temp.getId())).getName()));
                        currentLabel2.setText("Price: " + motherboard.get(Integer.parseInt(temp.getId())).getPrice() + "UAH");
                        currentLabel3.setText(String.valueOf(motherboard.get(Integer.parseInt(temp.getId())).getChipset()));
                        currentLabel4.setText(String.valueOf(motherboard.get(Integer.parseInt(temp.getId())).getFormFactor()));
                        currentLabel5.setText(String.valueOf(motherboard.get(Integer.parseInt(temp.getId())).getManufacturer()));
                        currentLabel6.setText(String.valueOf(motherboard.get(Integer.parseInt(temp.getId())).getMemoryType()));
                        configurationPC.setRam(null);  c5.setFill(Color.rgb(197, 197, 197));
                        configurationPC.setCasePC(null);  c8.setFill(Color.rgb(197, 197, 197));
                        totalPrice();
                        try {
                            saveBuildPC();
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                    grid.add(Item, column, row);
                    grid.add(temp, column++, row);
                    setMargin(Item, new Insets(7));
                }
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Motherboard> dataMotherboard() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system.motherboard join form_factor ON  form_factor.id_form_factor = motherboard.form_factor");
        List<Motherboard> list = new ArrayList<>();
        while (resultSet.next()) {
            Motherboard mbrd = new Motherboard();
            motherboardName.add(resultSet.getString("name"));
            mbrd.setId(resultSet.getString("id"));
            mbrd.setManufacturer(resultSet.getString("name"));
            mbrd.setPrice(resultSet.getString("price"));
            mbrd.setFormFactor("Form factor: " + resultSet.getString("form_factor.name_form_factor"));
            mbrd.setName(resultSet.getString("name"));
            mbrd.setMemoryType("Compatibility memory: " + resultSet.getString("memory_type") + "-" + resultSet.getString("max_memory_frequency"));
            mbrd.setSocket("Socket: " + resultSet.getString("socket"));
            mbrd.setChipset("Chipset: " + resultSet.getString("chipset"));
            mbrd.setIdFormFactor(Integer.parseInt(resultSet.getString("form_factor")));
            mbrd.setMaxMemoryFrequency(resultSet.getString("max_memory_frequency"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            mbrd.setImage(new Image(is));
            list.add(mbrd);
        }
        return list;
    }


    void loadItemCPU() throws SQLException, ClassNotFoundException {
        if (cpu.isEmpty()) {
            cpu = new ArrayList<>(dataCPU());
        }
        int column = 0;
        int row = 0;
        int index = 0;
        try {
            grid.getChildren().clear();
            for (CPU value : cpu) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane Item;
                Item = fxmlLoader.load();
                ItemController ItemController = fxmlLoader.getController();
                ItemController.setData(value);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                Button temp = new Button();
                temp.setId(String.valueOf(index));
                temp.setPrefSize(370, 180);
                temp.setStyle("-fx-background-color :  transparent");
                temp.setOnAction(event -> {
                    showAllLabels();
                    c1.setFill(Color.rgb(255, 255, 255));
                    configurationPC.setCpu(cpu.get(Integer.parseInt(temp.getId())));
                    currentItem.setImage(cpu.get(Integer.parseInt(temp.getId())).getImage());
                    currentLabel1.setText(String.valueOf(cpu.get(Integer.parseInt(temp.getId())).getName()));
                    currentLabel2.setText("Price: " + (cpu.get(Integer.parseInt(temp.getId())).getPrice()) + "UAH");
                    currentLabel3.setText(String.valueOf(cpu.get(Integer.parseInt(temp.getId())).getFrequency()));
                    currentLabel4.setText(String.valueOf(cpu.get(Integer.parseInt(temp.getId())).getThreads()));
                    currentLabel5.setText(("TDP: " + cpu.get(Integer.parseInt(temp.getId())).getTDP()) + "W");
                    currentLabel6.setText(String.valueOf(cpu.get(Integer.parseInt(temp.getId())).getCompatibilityMemory()));
                    configurationPC.setCpu(cpu.get(Integer.parseInt(temp.getId())));
                    configurationPC.setRam(null);  c5.setFill(Color.rgb(197, 197, 197));
                    configurationPC.setMotherboard(null);  c2.setFill(Color.rgb(197, 197, 197));
                    configurationPC.setPowerSupply(null);  c7.setFill(Color.rgb(197, 197, 197));
                    configurationPC.setCooling(null);  c3.setFill(Color.rgb(197, 197, 197));
                    totalPrice();
                    try {
                        saveBuildPC();
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });
                grid.add(Item, column, row);
                grid.add(temp, column++, row);
                setMargin(Item, new Insets(7));
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveBuildPC() throws SQLException, ClassNotFoundException {
        saveBuildButton.setVisible(configurationPC.getCpu() != null
                && configurationPC.getCooling() != null
                && configurationPC.getMotherboard() != null
                && configurationPC.getRam() != null
                && configurationPC.getMemory() != null
                && configurationPC.getPowerSupply() != null
                && configurationPC.getCasePC() != null);
    }

    private List<CPU> dataCPU() throws SQLException, ClassNotFoundException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getInfo("SELECT * FROM pc_config_system." + tableName);
        List<CPU> list = new ArrayList<>();
        while (resultSet.next()) {
            CPU cpu = new CPU();
            cpuName.add(resultSet.getString("name"));
            cpu.setName(resultSet.getString("name"));
            cpu.setPrice(resultSet.getString("price"));
            cpu.setCompatibilityMemory("Compatibility memory: " + resultSet.getString("memory_compatibility_type") + "-" + resultSet.getString("memory_compatibility_frequency"));
            cpu.setFrequency("Frequency: " + resultSet.getString("cpu_frequency") + " Mhz");
            cpu.setThreads("Threads: " + resultSet.getString("thread"));
            cpu.setSocket("Socket: " + resultSet.getString("socket"));
            cpu.setId((resultSet.getString("id")));
            cpu.setManufacturerId(Integer.parseInt((resultSet.getString("manufacturer_id"))));
            cpu.setTDP(resultSet.getString("tdp"));
            Blob blob = resultSet.getBlob("image");
            InputStream is = blob.getBinaryStream();
            cpu.setImage(new Image(is));
            list.add(cpu);
        }
        return list;
    }

    private int totalPrice() {
        int sum = 0;

        if (configurationPC.getCpu() != null) {
            sum += Integer.parseInt(configurationPC.getCpu().getPrice());
        }
        if (configurationPC.getMotherboard() != null) {
            sum += Integer.parseInt(configurationPC.getMotherboard().getPrice());
        }
        if (configurationPC.getCooling() != null) {
            sum += Integer.parseInt(configurationPC.getCooling().getPrice());
        }
        if (configurationPC.getGpu() != null) {
            sum += Integer.parseInt(configurationPC.getGpu().getPrice());
        }
        if (configurationPC.getRam() != null) {
            sum += Integer.parseInt(configurationPC.getRam().getPrice());
        }
        if (configurationPC.getMemory() != null) {
            sum += Integer.parseInt(configurationPC.getMemory().getPrice());
        }
        if (configurationPC.getCasePC() != null) {
            sum += Integer.parseInt(configurationPC.getCasePC().getPrice());
        }
        if (configurationPC.getPowerSupply() != null) {
            sum += Integer.parseInt(configurationPC.getPowerSupply().getPrice());
        }

        if (sum > 0) {
            totalPrice.setVisible(true);
            totalPriceLabel.setVisible(true);
            totalPrice.setText((sum) + "UAH");
        }
        return sum;
    }

    private void showOrderList() throws SQLException, ClassNotFoundException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet result = databaseHandler.getOrderList();
        int counter = 0;
        while (result.next()) {
            counter++;
            if (counter == 1) {
                orderLabel1.setVisible(true);
                priceLabel1.setVisible(true);
                orderLabel1.setText("Build #" + (result.getString("id_order")));
                priceLabel1.setText("price: " + result.getString("total_price"));
                orderLabel1.setTooltip(new Tooltip("Build id: " + result.getString("id_order") +
                        "\n Price: " + result.getString("total_price") +
                        "\n CPU: " + cpuName.get(Integer.parseInt(result.getString("id_cpu")) - 1) +
                        "\n Motherboard: " + motherboardName.get(Integer.parseInt(result.getString("id_motherboard")) - 2) +
                        "\n Cooling: " + coolingName.get(Integer.parseInt(result.getString("id_cooling_cpu")) - 1) +
                        "\n GPU: " + gpuName.get(Integer.parseInt(result.getString("id_gpu")) - 1) +
                        "\n RAM: " + ramName.get(Integer.parseInt(result.getString("id_ram")) - 1) +
                        "\n Memory: " + memoryName.get(Integer.parseInt(result.getString("id_memory")) - 1) +
                        "\n PowerSupply: " + powerSupplyName.get(Integer.parseInt(result.getString("id_power_supply")) - 1) +
                        "\n Case: " + caseName.get(Integer.parseInt(result.getString("id_case")) - 1)));
            } else if (counter == 2) {
                orderLabel2.setVisible(true);
                priceLabel2.setVisible(true);
                orderLabel2.setText("Build #" + (result.getString("id_order")));
                priceLabel2.setText("price: " + result.getString("total_price"));
                orderLabel2.setTooltip(new Tooltip("Build id: " + result.getString("id_order") +
                        "\n Price: " + result.getString("total_price") +
                        "\n CPU: " + cpuName.get(Integer.parseInt(result.getString("id_cpu")) - 1) +
                        "\n Motherboard: " + motherboardName.get(Integer.parseInt(result.getString("id_motherboard")) - 2) +
                        "\n Cooling: " + coolingName.get(Integer.parseInt(result.getString("id_cooling_cpu")) - 1) +
                        "\n GPU: " + gpuName.get(Integer.parseInt(result.getString("id_gpu")) - 1) +
                        "\n RAM: " + ramName.get(Integer.parseInt(result.getString("id_ram")) - 1) +
                        "\n Memory: " + memoryName.get(Integer.parseInt(result.getString("id_memory")) - 1) +
                        "\n PowerSupply: " + powerSupplyName.get(Integer.parseInt(result.getString("id_power_supply")) - 1) +
                        "\n Case: " + caseName.get(Integer.parseInt(result.getString("id_case")) - 1)));
            } else if (counter == 3) {
                orderLabel3.setTooltip(new Tooltip("Build id: " + result.getString("id_order") +
                        "\n Price: " + result.getString("total_price") +
                        "\n CPU: " + cpuName.get(Integer.parseInt(result.getString("id_cpu")) - 1) +
                        "\n Motherboard: " + motherboardName.get(Integer.parseInt(result.getString("id_motherboard")) - 2) +
                        "\n Cooling: " + coolingName.get(Integer.parseInt(result.getString("id_cooling_cpu")) - 1) +
                        "\n GPU: " + gpuName.get(Integer.parseInt(result.getString("id_gpu")) - 1) +
                        "\n RAM: " + ramName.get(Integer.parseInt(result.getString("id_ram")) - 1) +
                        "\n Memory: " + memoryName.get(Integer.parseInt(result.getString("id_memory")) - 1) +
                        "\n PowerSupply: " + powerSupplyName.get(Integer.parseInt(result.getString("id_power_supply")) - 1) +
                        "\n Case: " + caseName.get(Integer.parseInt(result.getString("id_case")) - 1)));
                orderLabel3.setVisible(true);
                priceLabel3.setVisible(true);
                orderLabel3.setText("Build #" + (result.getString("id_order")));
                priceLabel3.setText("price: " + result.getString("total_price"));
            }
        }

    }
}

