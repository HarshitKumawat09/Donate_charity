import React, {useEffect, useState} from "react";
import '../styles/login.css';


function Login() {

    const [fetchResponse, handleFetchResponse] = useState();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();

        fetch('http://localhost:8080/project/api/login', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
            .then((response) => {
                console.log('Response Headers:', response.headers);
                return response.json();
            })
            .then((response) => {
                if (response?.data) {
                    localStorage.setItem("uid", response.id);
                    localStorage.setItem("username", response.username);
                    console.log(response.id);
                    window.location.href ="/dashboard";
                }
            });
    }

    return (
        <body>
        <div className="container">
            <div className="title">Welcome Back!</div>
            <form className="login-container" onSubmit={handleSubmit}>
                <div className="input-container">
                    <label>username </label>
                    <input
                        id="username"
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                    />
                </div>
                <div className="input-container">
                    <label>password </label>
                    <input
                        id="password"
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="log-button">log in</button>
            </form>


            {/*<button className ="log-button" onClick={() => {*/}
            {/*    fetch('http://localhost:8080/project/api/register', {*/}
            {/*        method: "POST",*/}
            {/*        headers: {*/}
            {/*            "Content-Type": "application/json"*/}
            {/*        },*/}
            {/*        body: JSON.stringify({*/}
            {/*            username: username,*/}
            {/*            password: password,*/}
            {/*            fails: fails*/}
            {/*        })*/}
            {/*    })*/}
            {/*        .then((response) => {*/}
            {/*            console.log('Response Headers:', response.headers);*/}
            {/*            return response.json();*/}
            {/*        })*/}
            {/*        .then((response) => {*/}
            {/*            if (response?.fails === 3) {*/}
            {/*                window.location.href = "/AccountBlockedPage";*/}
            {/*            }*/}
            {/*            else if (response?.data) {*/}
            {/*                setFails(response.fails);*/}
            {/*                handleFetchResponse(response.data);*/}
            {/*            }*/}
            {/*        });*/}
            {/*}}>*/}
            {/*    log in*/}
            {/*</button>*/}
            {fetchResponse ? <p>{fetchResponse}</p> : null}
        </div>
        </body>
    )
}

export default Login;
