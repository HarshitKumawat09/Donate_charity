import React, { useEffect } from "react";

function Organization({ name, websiteUrl, coverImageUrl, slug }) {

    return (
        <div className="organization">
            <div
                className="organization-image"
                style={{
                    backgroundImage: `url(${coverImageUrl})`,
                    position: "relative",
                    width: "100%",
                    height: "200px", // Set the desired height for the organization card
                    backgroundSize: "cover",
                    backgroundPosition: "center",
                    borderRadius: "12px"
                }}
            >
                <div
                    className="overlay"
                    style={{
                        position: "absolute",
                        bottom: "0",
                        left: "0",
                        width: "100%",
                        padding: "8px",
                        display: "flex",
                        flexDirection: "row",
                        justifyContent: "space-between",
                        alignItems: "center",
                        flex: "column"
                    }}
                >
                    <a href={`${websiteUrl}`} target="_blank"
                       style={{textDecoration: "underline", color: "white", flex: "1"}}>
                        <h3 style={{
                            fontFamily: "Montserrat, sans-serif",
                            color: "white",
                            fontWeight: 600,
                            fontSize: "14px",
                            maxWidth: "80%",
                            width: "100%",
                            margin: "0",
                            textAlign: "left"
                        }}>
                            {name}
                        </h3>
                    </a>
                    <a
                        data-every-style
                        href={`https://www.every.org/${slug}#/donate`}
                        style={{color: "white", textDecoration: "none", textAlign: "right", marginRight: "20px"}}
                    >
                        Donate
                    </a>
                </div>
            </div>
        </div>
    );
}

export default Organization;
