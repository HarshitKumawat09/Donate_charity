import React, {useState} from "react";
import "../styles/signup.css"

function SignUp() {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await fetch('http://localhost:8080/project/api/register', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    username: username,
                    password: password,
                    email: email
                })
            });

            if (!response.ok) {
                throw new Error('Network response was not ok');
            }

            const responseData = await response.json();
            if (responseData?.data) {
                localStorage.setItem("uid", responseData.id);
                localStorage.setItem("username", responseData.username);
                window.location.href ="/preferences";
            }

            // Handle the response data as needed
        } catch (error) {
            console.error('There was a problem with your fetch operation:', error);
        }

        // Reset form fields after submission (optional)
        setUsername('');
        setEmail('');
        setPassword('');
    };

    return (
        <div>
            <div className="signupContainer">
                <div style={{ fontSize: '30px' }}>Welcome!</div>
                <form className="form-input" onSubmit={handleSubmit}>
                    <div>
                        <label>username </label>
                        <input
                            id="username"
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>email </label>
                        <input
                            id="email"
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                        />
                    </div>
                    <div>
                        <label>I am</label>
                        <input
                            id="over18"
                            type="radio"
                            value="over18"
                            required
                        />
                        <label className="age-label" htmlFor="over18">18 or older</label>
                    </div>
                    <div>
                        <label>I am</label>
                        <input
                            id="individual"
                            type="radio"
                            value="individual"
                            name="userType"
                            required
                        />
                        <label htmlFor="individual">an individual</label>
                        <input
                            id="organization"
                            type="radio"
                            value="organization"
                            name="userType"
                        />
                        <label className="organization-label" htmlFor="organization">an organization</label>
                    </div>
                    <div>
                        <label>password </label>
                        <input
                            id="password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>
                    <div className={"signbutton-container"}>
                        <button type="submit" className={"sign-button"}>sign up</button>
                    </div>
                </form>

                <div className={"errorMsg"}></div>
            </div>
        </div>
    );
}

export default SignUp;