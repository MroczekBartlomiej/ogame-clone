package pl.grizwold.ogame.modules.buildings.events.construction.listeners;

import org.springframework.context.event.EventListener;
import pl.grizwold.ogame.common.domain.Event;
import pl.grizwold.ogame.modules.buildings.domain.Building;
import pl.grizwold.ogame.modules.buildings.domain.ConstructionSite;
import pl.grizwold.ogame.modules.buildings.events.construction.domain.BuildingConstructed;
import pl.grizwold.ogame.modules.buildings.events.construction.domain.BuildingConstructionFinished;
import pl.grizwold.ogame.modules.resources.events.domain.ResourcesLeaseUsed;

import java.util.Arrays;
import java.util.List;

public class BuildingConstructionFinishedListener {

    @EventListener(BuildingConstructionFinished.class)
    public List<Event> execute(BuildingConstructionFinished event) {
        ConstructionSite constructionSite = getConstructionSite(event.getCorrelationToken());

        checkResourcesLease(event.getCorrelationToken());
        checkConstructionSite(event.getCorrelationToken());
        saveBuilding(constructionSite.getTargetBuildingState());

        Event destroyLease = createResourcesLeaseUsedEvent(event);
        Event buildingConstructed = createBuildingConstructedEvent(constructionSite.getTargetBuildingState());

        return Arrays.asList(destroyLease, buildingConstructed);
    }

    private ConstructionSite getConstructionSite(String constructionSiteId) {
        return null;
    }

    private void checkResourcesLease(String resourcesLeaseId) {
        // validate existence of resource lease (construction could be canceled?)
    }

    private void checkConstructionSite(String constructionSiteId) {
        // validate existence of construction site (could be removed/canceled?)
    }

    private Event createResourcesLeaseUsedEvent(Event source) {
        return createDestroyResourceLeaseEvent(source);
    }

    private void saveBuilding(Building building) {
        // save changed building in modules DB
    }

    private ResourcesLeaseUsed createDestroyResourceLeaseEvent(Event source) {
        return new ResourcesLeaseUsed(source);
    }

    private BuildingConstructed createBuildingConstructedEvent(Building leveledUpBuilding) {
        return null;
    }
}
