package wooteco.subway.station;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.springframework.util.ReflectionUtils;

public class StationDao {

    private static Long seq = 0L;
    private static List<Station> stations = new ArrayList<>();

    public static Station save(Station station) {
        Station persistStation = createNewObject(station);
        stations.add(persistStation);
        return persistStation;
    }

    public static List<Station> findAll() {
        return stations;
    }

    public static void delete(Long id) {
        stations.stream()
            .filter(station -> station.getId() == id)
            .findFirst()
            .ifPresent(station -> stations.remove(station));
    }

    private static Station createNewObject(Station station) {
        Field field = ReflectionUtils.findField(Station.class, "id");
        field.setAccessible(true);
        ReflectionUtils.setField(field, station, ++seq);
        return station;
    }
}
