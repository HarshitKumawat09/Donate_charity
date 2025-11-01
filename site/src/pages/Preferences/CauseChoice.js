import { useState } from "react";
import "./CauseChoice.css";

function CauseChoice({ filterName, updateData, formData }) {
    const isSelected = formData.causes.includes(filterName);
    const [isClicked, setIsClicked] = useState(isSelected);

    const handleClick = () => {
        const updatedData = {
            ...formData,
            causes: isSelected
                ? formData.causes.filter((d) => d !== filterName)
                : [...formData.causes, filterName],
        };
        updateData(updatedData);
        setIsClicked(!isClicked);
    };

    return (
        <div
            onClick={handleClick}
            className={`filter-choice ${isClicked ? "when-clicked" : "when-not-clicked"}`}
        >
            <h1 className="filter-text">{filterName}</h1>
        </div>
    );
}

export default CauseChoice;
