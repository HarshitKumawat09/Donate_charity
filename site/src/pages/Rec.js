import React, {useEffect, useState} from "react";
import "../styles/rec.css"
import Organization from "../components/Organization"
import organization from "../components/Organization";

function Rec() {
    const [heartSrc, setHeartSrc] = useState("/assets/Heart.png");
    const [hearted, setHearted] = useState(false);
    // const [preferences, setPreferences] = useState([]);
    const [organizations, setOrganizations] = useState([]);

    const uid = parseInt(localStorage.getItem("uid"), 10);
  
    // const toggleHeart = () => {
    //     if (!hearted) {
    //         setHeartSrc("/assets/Favorite.png");
    //     } else {
    //         setHeartSrc("/assets/Heart.png");
    //     }
    //     setHearted(!hearted);
    // };

    function saveOrg(org) {
        try {
            const response = fetch('http://localhost:8080/project/api/organization/save', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(org)
            });

            if (response.ok) {
                const data = response.text();
                console.log(data); // Log success message
            } else {
                console.error('Failed to save organization');
            }
        } catch (error) {
            console.error('Error saving organization:', error.message);
        }
    }

    useEffect(() => {
        // Fetch user preferences and convert them into a list of strings
        fetchPreferences();
    }, []);

    const user = Number(localStorage.getItem("uid"));
    const fetchPreferences = () => {
        fetch("http://localhost:8080/project/api/preferences/post", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ userId: user })
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to fetch user preferences");
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
                // Extract preferences from the response
                const preferencesList = data.data;

                // Call API for each preference
                preferencesList.forEach(preference => {
                    getOrganizations(preference);
                });

            })
            .catch(error => {
                console.error("Error:", error);
            });
    };

    const getOrganizations = (preference) => {
        fetch("https://partners.every.org/v0.2/browse/"+preference+"?apiKey=pk_live_9033944ae6aeeb2c9bc83bd2029060d9")
            .then(response => {
                if (!response.ok) {
                    throw new Error("Failed to fetch organizations");
                }
                return response.json();
            })
            .then(data => {
                // Retrieve the first organization from the response
                const organization = data.nonprofits[0];
                if (organization) {
                    // Extract organization data
                    const organizationData = {
                        name: organization.name,
                        websiteUrl: organization.websiteUrl,
                        coverImageUrl: organization.coverImageUrl,
                        slug: organization.slug,
                        userId: uid
                    };

                    console.log(organizationData);

                    const org = {
                        name: organization.name,
                        url: organization.websiteUrl,
                        image: organization.coverImageUrl,
                        slug: organization.slug,
                        userId: uid
                    };

                    saveOrg(org);
                    // Handle the organization data
                    setOrganizations(prevOrganizations => [...prevOrganizations, organizationData]);

                    console.log("Organization saved for preference: ", preference, ":", organizationData);
                } else {
                    console.log("No organization found for preference", preference);
                }
            })
            .catch(error => {
                // Handle any errors
                console.error("Error:", error);
            });
    }
    return (
        <div className="rec-container">
            <div className="header">
                <h2>Great! Here are our recommendations:</h2>
                <p>We have saved these organizations to your profile.</p>
            </div>
            <div className={"organization-results"}>
                {organizations.map((org) => (
                    <div className="individual-organization" style={{maxWidth: "20rem"}}>
                    <Organization
                        name={org.name}
                        websiteUrl={org.websiteUrl}
                        coverImageUrl={org.coverImageUrl}
                        slug={org.slug}
                    />
                    </div>
                ))}
            </div>
            {/*<div className="rec-list">*/}
            {/*    <div className="example1">*/}
            {/*        <img className="heart" src={heartSrc} alt="Heart" onClick={toggleHeart}/>*/}
            {/*        <p>Save the Crocodiles</p>*/}
            {/*        <a><img className="external-icon" src="/assets/External%20Link.png" alt="Link"/></a>*/}
            {/*    </div>*/}
            {/*    <div className="example2">*/}
            {/*        <img className="heart" src={heartSrc} alt="Heart" onClick={toggleHeart}/>*/}
            {/*        <p>Save the Capybaras</p>*/}
            {/*        <a><img className="external-icon" src="/assets/External%20Link.png" alt="Link"/></a>*/}
            {/*    </div>*/}

            {/*</div>*/}
            {/*<div className="see-more-div"><a className="see-more">see more</a></div>*/}
            <button onClick={() => window.location.href = "/dashboard"}>done</button>


        </div>
    );
}

export default Rec;
