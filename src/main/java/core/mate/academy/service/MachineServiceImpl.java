package core.mate.academy.service;

import core.mate.academy.model.Machine;
import core.mate.academy.model.MachineTypes;
import core.mate.academy.service.producer.MachineProducer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MachineServiceImpl implements MachineService<Machine> {
    private final HashMap<Class<? extends Machine>, MachineProducer> machineTypeMap = mapCreate();

    @Override
    public List<Machine> getAll(Class<? extends Machine> type) {
        if (machineTypeMap.containsKey(type)) {
            return new ArrayList<>(machineTypeMap.get(type).get());
        }
        return Collections.emptyList();
    }

    @Override
    public void fill(List<? super Machine> machines, Machine value) {
        Collections.fill(machines, value);
    }

    @Override
    public void startWorking(List<? extends Machine> machines) {
        for (Machine machine: machines) {
            machine.doWork();
        }
    }

    private HashMap<Class<? extends Machine>, MachineProducer> mapCreate() {
        HashMap<Class<? extends Machine>, MachineProducer> hashMap = new HashMap<>();
        for (MachineTypes machine : MachineTypes.values()) {
            hashMap.put(machine.getMachineClass(), machine.getMachineProducer());
        }
        return hashMap;
    }
}
