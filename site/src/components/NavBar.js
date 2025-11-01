import React from "react";
import "./NavBar.css";

function NavBar() {
    const uid = localStorage.getItem("uid");
    const handleLogout = () => {
        localStorage.removeItem("uid");
        window.location.href = "/login";
    };


    return (
        <nav className="navbar">
            <div className="navbar_logo">
                <img src="/assets/logo.png" alt="Donate Logo" style={{ cursor: "pointer" }} onClick={() => { window.location.href = "/"; }}/>
            </div>
            <div className="navbar_buttons">
                {uid ? ( // Check if uid is truthy (i.e., not empty)
                    <button className="logout-button" onClick={handleLogout}>Logout</button>
                ) : (
                    <React.Fragment>
                        <button className="login-button" onClick={() => { window.location.href = "/login"; }}>Log in</button>
                        <button className="signup-button" onClick={() => { window.location.href = "/signup"; }}>Sign up</button>
                    </React.Fragment>
                )}
            </div>
        </nav>
    );
}

export default NavBar;
