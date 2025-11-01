import React, {useEffect, useState} from "react";
import "../styles/dashboard.css"
import Organization from "../components/Organization";

function Dashboard() {
    const uid = parseInt(localStorage.getItem("uid"), 10);
    const username = localStorage.getItem("username");
    const [causes, setCauses] = useState([]);
    const [top, setTop] = useState("")
    const [month, setMonth] = useState(0);
    // if (username === "tommy") {
    //     setMonth(3);
    // }

    useEffect( () => {
        if (username === "tommy") {
            setMonth(3);
        }
    }, []);

    const [orgs, setOrgs] = useState([]);

    useEffect(() => {
        // Fetch organizations data from the server
        fetch("http://localhost:8080/project/api/organizations/post", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                userId: uid // Replace yourUserId with the actual user ID
            })
        })
            .then(response => response.json())
            .then(data => setOrgs(data))
            .catch(error => console.error("Error fetching organizations data:", error));
    }, []); // Run only once when the component mounts

    useEffect(() => {
        // Function to fetch user preferences
        const fetchUserPreferences = async () => {
            try {
                const response = await fetch("http://localhost:8080/project/api/preferences/post", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({userId: uid})
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch user preferences. response not ok');
                }
                const responseData = await response.json();
                setCauses(responseData.data);
            } catch (error) {
                console.error('There was a problem with your fetch operation:', error);
            }
        };
        fetchUserPreferences();
    },[]);
    useEffect(() => {
        const fetchUserTop = async () => {
            try {
                const response = await fetch("http://localhost:8080/project/api/date/top", {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({userId: uid})
                });
                if (!response.ok) {
                    throw new Error('Failed to fetch user preferences. response not ok');
                }
                const responseData = await response.json();
                setTop(responseData.data);
            } catch (error) {
                console.error('There was a problem with your fetch operation:', error);
            }
        };
        fetchUserTop();
    },[]);

    const handleDonateClick = (slug) => {
        window.open(`https://www.every.org/${slug}#/donate`, '_blank');
    };

    return (
        <body>
            <div className="main-main-container">

                <div className="title">Welcome back, {username}!</div>

                <div className="main-container">
                    {month === 0 && ( // Check if month is not equal to 0
                        <div className="graph">
                            <div className="textbox">
                                you have no donation history

                            </div>
                        </div>
                    )}
                    {username === "tommy" && ( // Check if month is not equal to 0
                        <div className="graph">
                            <iframe
                                title="Taipy"
                                src="http://127.0.0.1:5000/" // Replace this URL with the URL of your Python application
                                id="graph-taipy"
                            ></iframe>
                        </div>
                    )}
                    {/*<div className="graph">*/}
                    {/*    <iframe*/}
                    {/*        title="Taipy"*/}
                    {/*        src="http://127.0.0.1:5000/" // Replace this URL with the URL of your Python application*/}
                    {/*        id="graph-taipy"*/}
                    {/*    ></iframe>*/}
                    {/*</div>*/}
                    <div className="textbox">
                        <div className="donation-streak-text">
                            {username === "tommy" ? (
                                <>
                                    You’ve had a donation streak of {3} months. Your top organization is {`Lil Bub's Big Fund`}.
                                </>
                            ) : (
                                <>
                                    You’ve had a donation streak of {month} months. Your top organization is {top}.
                                </>
                            )}
                        </div>
                        <div className="donation-streak-text">
                            {username === "tommy" ? (
                                <>
                                    You’re making a difference! Keep it up :)
                                </>
                            ) : (
                                <>
                                    You're getting started on your journey! That's so exciting.
                                </>
                            )}
                        </div>
                    </div>

                    <div className="bottom-left-container">
                        <div className="causes-container">
                            <div className="causes-title-container">
                                <div className="causes-label">
                                    Causes
                                </div>
                                <a className="link" onClick={() => window.location.href = "/preferences"}>
                                    edit
                                </a>
                            </div>

                            <div className="causes-list">
                                {causes.map(cause => (
                                    <p className="cause">{cause}</p>
                                ))}
                            </div>
                        </div>

                        <div>
                            <button onClick={() => window.location.href = "/recommendation"} className="rec-orgs-button">see my recommendations</button>
                        </div>
                    </div>
                    <div className="bottom-right-container">
                        <div className="causes-title-container">
                            <div className="causes-label">Top organizations:</div>
                            <a className="link" href="">
                                see all
                            </a>
                        </div>
                        <div className="saved-org-table">
                            <table>
                                {orgs.map(org => (
                                    <tr key={org.id}>
                                        <td>
                                            {org.name}
                                            <a href={org.url} target="_blank">
                                                <img className="ex-link" src="/assets/external_link.png"></img>
                                            </a>
                                        </td>
                                        <td className="button-col">
                                            <button className="donate-button" onClick={() => handleDonateClick(org.slug)}>Donate</button>
                                        </td>
                                    </tr>
                                ))}
                            </table>
                        </div>

                    </div>
                </div>
            </div>

        </body>
    );
}

export default Dashboard;