import oshi.SystemInfo;
import oshi.hardware.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hal = systemInfo.getHardware();

        // Получение информации о процессоре
        CentralProcessor processor = hal.getProcessor();
        System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
        System.out.println("Cores: " + processor.getLogicalProcessorCount());
        System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());

        // Получение информации о видеокартах
        List<GraphicsCard> graphicsCards = hal.getGraphicsCards();
        graphicsCards.forEach(e -> {
            System.out.println(e.getName());
            System.out.println(e.getDeviceId());
            System.out.println(e.getVRam() / (1024 * 1024) + " MB");

        });

        // Получение информации о материнской плате
        Baseboard motherboard = hal.getComputerSystem().getBaseboard();
        System.out.println("Motherboard: " + motherboard.getManufacturer() + " " + motherboard.getModel());

        // Получение информации о памяти
        GlobalMemory memory = hal.getMemory();
        System.out.println("Total Memory: " + memory.getTotal() / (1024 * 1024) + " MB");
        System.out.println("Available Memory: " + memory.getAvailable() / (1024 * 1024) + " MB");

        List<HWDiskStore> hwDiskStores = hal.getDiskStores();

        hwDiskStores.forEach(e -> {
            System.out.println(e.getName());
            System.out.println(e.getReadBytes() / (1024 * 1024) + " MB");
        });
    }
}

