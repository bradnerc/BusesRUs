package ca.ubc.cs.cpsc210.translink.ui;

import android.content.Context;
import ca.ubc.cs.cpsc210.translink.BusesAreUs;
import ca.ubc.cs.cpsc210.translink.model.Route;
import ca.ubc.cs.cpsc210.translink.model.RoutePattern;
import ca.ubc.cs.cpsc210.translink.model.Stop;
import ca.ubc.cs.cpsc210.translink.model.StopManager;
import ca.ubc.cs.cpsc210.translink.util.Geometry;
import ca.ubc.cs.cpsc210.translink.util.LatLon;
import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.bonuspack.overlays.Polyline;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.*;

// A bus route drawer
public class BusRouteDrawer extends MapViewOverlay {
    /** overlay used to display bus route legend text on a layer above the map */
    private BusRouteLegendOverlay busRouteLegendOverlay;
    /** overlays used to plot bus routes */
    private List<Polyline> busRouteOverlays;

    /**
     * Constructor
     * @param context   the application context
     * @param mapView   the map view
     */
    public BusRouteDrawer(Context context, MapView mapView) {
        super(context, mapView);
        busRouteLegendOverlay = createBusRouteLegendOverlay();
        busRouteOverlays = new ArrayList<>();
    }

    /**
     * Plot each visible segment of each route pattern of each route going through the selected stop.
     */
    public void plotRoutes(int zoomLevel) {
        //TODO: complete the implementation of this method (Task 7)\

        /* //original implementation
        updateVisibleArea();

        if (StopManager.getInstance().getSelected() != null) {
            Set<Route> temp = StopManager.getInstance().getSelected().getRoutes();
            busRouteLegendOverlay.clear();
            busRouteOverlays.clear();

            for (Route rp : temp) {
                int brc = busRouteLegendOverlay.add(rp.getNumber());
                for (RoutePattern rps : rp.getPatterns()) {
                    Polyline pl = new Polyline(context);

                    pl.setColor(busRouteLegendOverlay.getColor(rp.getNumber()));
                    pl.setWidth(getLineWidth(zoomLevel));

                    List<GeoPoint> geopoints = new LinkedList<>();

                    int x = 0;
                    int size = rps.getPath().size();

                    for (LatLon ll : rps.getPath()) {
                        if (x < (size - 1)) {
                            if (Geometry.rectangleIntersectsLine(northWest, southEast, rps.getPath().get(x), rps.getPath().get(x + 1))) {
                                GeoPoint gp = Geometry.gpFromLL(ll);
                                geopoints.add(gp);
                            }
                        }
                        x++;
                    }
                    pl.setPoints(geopoints);
                    pl.setVisible(true);
                    busRouteOverlays.add(pl);
                }
            }
        }
        */


        /* implementation with severe lag
        updateVisibleArea();

        if (StopManager.getInstance().getSelected() != null) {
            Set<Route> temp = StopManager.getInstance().getSelected().getRoutes();
            busRouteLegendOverlay.clear();
            busRouteOverlays.clear();

            for (Route rp : temp) {
                for (RoutePattern rps : rp.getPatterns()) {
                    List<GeoPoint> gp = new LinkedList<>();
                    for (LatLon ll : rps.getPath()){
                        Polyline pl = new Polyline(context);
                        busRouteLegendOverlay.add(rp.getNumber());
                        pl.setColor(busRouteLegendOverlay.getColor(rp.getNumber()));
                        pl.setWidth(getLineWidth(zoomLevel));
                        GeoPoint geoPoint =Geometry.gpFromLL(ll);
                        gp.add(geoPoint);
                        pl.setPoints(gp);
                        pl.setVisible(true);
                        busRouteOverlays.add(pl);
                    }
                }
            }
        }
        */


        /*
        updateVisibleArea();

        if (StopManager.getInstance().getSelected() != null) {
            Set<Route> temp = StopManager.getInstance().getSelected().getRoutes();
            busRouteLegendOverlay.clear();
            busRouteOverlays.clear();
            for (Route rp : temp) {
                busRouteLegendOverlay.add(rp.getNumber());
                for (RoutePattern rps : rp.getPatterns()) {
                    List<Polyline> pls = new LinkedList<>();
                    int size = rps.getPath().size();
                    int x = 0;
                    for (LatLon ll : rps.getPath()) {
                        if (x < (size - 1)) {
                            if (Geometry.rectangleIntersectsLine(northWest, southEast, ll, rps.getPath().get(x+1))) {
                                List<GeoPoint> gp = new LinkedList<>();
                                Polyline pl = new Polyline(context);
                                pl.setColor(busRouteLegendOverlay.getColor(rp.getNumber()));
                                pl.setWidth(getLineWidth(zoomLevel));
                                GeoPoint geoPoint = Geometry.gpFromLL(ll);
                                GeoPoint geoPoint1 = Geometry.gpFromLL(rps.getPath().get(x+1));
                                gp.add(geoPoint);
                                gp.add(geoPoint1);
                                pl.setPoints(gp);
                                pl.setVisible(true);
                                pls.add(pl);
                            }
                        }
                        for (Polyline polyline : pls) {
                            busRouteOverlays.add(polyline);
                        }
                        x++;
                    }
                }
            }
        }
        */

        updateVisibleArea();

        if (StopManager.getInstance().getSelected() != null) {
            Set<Route> temp = StopManager.getInstance().getSelected().getRoutes();
            busRouteLegendOverlay.clear();
            busRouteOverlays.clear();
            for (Route rp : temp) {
                busRouteLegendOverlay.add(rp.getNumber());

                for (RoutePattern rps : rp.getPatterns()) {
                    int size = rps.getPath().size();
                    int x = 0;

                    List<Polyline> pls = new LinkedList<>();
                    List<GeoPoint> gp = new LinkedList<>();
                    Polyline pl = new Polyline(context);
                    pl.setColor(busRouteLegendOverlay.getColor(rp.getNumber()));
                    pl.setWidth(getLineWidth(zoomLevel));

                    for (LatLon ll : rps.getPath()) {
                        if (x < (size - 1)) {
                            GeoPoint geoPoint = Geometry.gpFromLL(ll);
                            GeoPoint geoPoint1 = Geometry.gpFromLL(rps.getPath().get(x + 1));
                            if (Geometry.rectangleIntersectsLine(northWest, southEast, ll, rps.getPath().get(x + 1))) {
                                gp.add(geoPoint);
                                gp.add(geoPoint1);
                                x++;
                            } else {
                                gp.add(geoPoint);
                                x++;
                            }
                        }
                    }

                    pl.setVisible(true);
                    pl.setPoints(gp);
                    pls.add(pl);

                    for (Polyline polyline : pls)
                        busRouteOverlays.add(polyline);
                }
            }
        }
    }

    public List<Polyline> getBusRouteOverlays() {
        return Collections.unmodifiableList(busRouteOverlays);
    }

    public BusRouteLegendOverlay getBusRouteLegendOverlay() {
        return busRouteLegendOverlay;
    }


    /**
     * Create text overlay to display bus route colours
     */
    private BusRouteLegendOverlay createBusRouteLegendOverlay() {
        ResourceProxy rp = new DefaultResourceProxyImpl(context);
        return new BusRouteLegendOverlay(rp, BusesAreUs.dpiFactor());
    }

    /**
     * Get width of line used to plot bus route based on zoom level
     * @param zoomLevel   the zoom level of the map
     * @return            width of line used to plot bus route
     */
    private float getLineWidth(int zoomLevel) {
        if(zoomLevel > 14)
            return 7.0f * BusesAreUs.dpiFactor();
        else if(zoomLevel > 10)
            return 5.0f * BusesAreUs.dpiFactor();
        else
            return 2.0f * BusesAreUs.dpiFactor();
    }
}
