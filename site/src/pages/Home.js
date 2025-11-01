import React from "react";
import "./Home.css";

function Home() {
    return (
        <div>
            <div className="demo_images">
                <div className="column_1">
                    <div className="image_container3">
                        <img src="/assets/climatechange.jpg" alt="Climate change is real"/>
                    </div>
                    <div className="image_container3">
                        <img src="/assets/food.jpg" alt="Food drive"/>
                    </div>
                    <div className="image_container3">
                        <img src="/assets/forestfire.jpg" alt="Forest fires"/>
                    </div>
                </div>
                <div className="column_2">
                    <div className="image_container2">
                        <img src="/assets/homelessness.jpg" alt="Homelessness"/>
                    </div>
                    <div className="image_container2">
                        <img src="/assets/homeless.jpg" alt="Homelessness"/>
                    </div>
                </div>
                <div className="column_3">
                    <div className="image_container3">
                    <img src="/assets/straycat.jpg" alt="Stray cats"/>
                    </div>
                    <div className="image_container3">
                        <img src="/assets/war.jpg" alt="War is causing terror"/>
                    </div>
                    <div className="image_container3">
                        <img src="/assets/homeless2.jpg" alt="Homelessness"/>
                    </div>
                </div>
                <div className="column_4">
                    <div className="image_container2">
                        <img src="/assets/straydog.jpg" alt="Stray dog"/>
                    </div>
                    <div className="image_container2">
                        <img src="/assets/oceanpollution.jpg" alt="Ocean pollution"/>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Home;