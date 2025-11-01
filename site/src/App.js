import React from "react";
import {Navigate, Route, Routes} from "react-router-dom";
import NavBar from "./components/NavBar"
import Login from "./pages/Login";
import SignUp from "./pages/SignUp";
import Home from "./pages/Home";
import Preferences from "./pages/Preferences/Preferences";
import Rec from "./pages/Rec";
import Dashboard from "./pages/Dashboard";
import Organization from "./components/Organization"
import { AuthProvider } from "./AuthContext";
import PrivateRoute from "./PrivateRoute";

function App() {
    return (
        <div>
            <NavBar/>
            <AuthProvider>
            <Routes>
                <Route path="/" element={<Home />}/>
                <Route path="/login" element={<Login />}/>
                <Route path="/" element={<PrivateRoute>
                    <Home/>
                </PrivateRoute>}/>
                <Route path="/signup" element={<SignUp />}/>
                <Route path="/dashboard" element={<Dashboard />}/>
                <Route path="/preferences" element={<Preferences />}/>
                <Route path="/recommendation" element={<Rec />}/>
                <Route path="/org" element={<Organization
                    name="Save the SNails"
                    websiteUrl="google.com"
                    coverImageUrl="https://res.cloudinary.com/everydotorg/image/upload/f_auto,c_limit,w_3840,q_80/profile_pics/ipxxsfqxtt6skku7vh1z"
                    slug="lilbubsbigfund"
                />}/>
                {/*<Route path="*" element={<Navigate to="/" replace />}/>*/}
            </Routes>
            </AuthProvider>
        </div>
    )
}

export default App;