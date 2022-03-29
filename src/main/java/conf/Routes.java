package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.application.ApplicationRoutes;
import ninja.utils.NinjaProperties;

import com.google.inject.Inject;

import controllers.ApplicationController;
import controllers.AuthController;
import controllers.FlightController;

public class Routes implements ApplicationRoutes {
    
    @Inject
    NinjaProperties ninjaProperties;

    /**
     * Using a (almost) nice DSL we can configure the router.
     * 
     * The second argument NinjaModuleDemoRouter contains all routes of a
     * submodule. By simply injecting it we activate the routes.
     * 
     * @param router
     *            The default router of this application
     */
    @Override
    public void init(Router router) {  
        
        ///////////////////////////////////////////////////////////////////////
        // Login / Logout
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/login").with(AuthController::login);
        router.POST().route("/signup").with(AuthController::signup);
        router.POST().route("/login").with(AuthController::loginPost);
        router.GET().route("/logout").with(AuthController::logout);
        
        //////////////////////////////////////////////////////////////////////
        // Flight save and get
        //////////////////////////////////////////////////////////////////////

        router.GET().route("/flights").with(FlightController::getAllFlights);
        
        router.GET().route("/flights/{id}").with(FlightController::getFlight);
        router.POST().route("/flight").with(FlightController::saveFlight);
        router.PUT().route("/flight").with(FlightController::updateFlight);
        router.DELETE().route("/flight").with(FlightController::deleteFlight);
        ///////////////////////////////////////////////////////////////////////
        // Assets (pictures / javascript)
        ///////////////////////////////////////////////////////////////////////    
        router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
        router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
        
        ///////////////////////////////////////////////////////////////////////
        // Index / Catchall shows index page
        ///////////////////////////////////////////////////////////////////////
        router.GET().route("/.*").with(ApplicationController::index);
        router.OPTIONS().route("/.*").with(ApplicationController::index);
    }

}