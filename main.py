import taipy as tp
from taipy import Config, Core, Gui



################################################################
#            Configure application                             #
################################################################
def build_message(name):
    return f"Hello {name}!"



# A first data node configuration to model an input name.
input_name_data_node_cfg = Config.configure_data_node(id="input_name")
# A second data node configuration to model the message to display.
message_data_node_cfg = Config.configure_data_node(id="message")
# A task configuration to model the build_message function.
build_msg_task_cfg = Config.configure_task("build_msg", build_message, input_name_data_node_cfg, message_data_node_cfg)
# The scenario configuration represents the whole execution graph.
scenario_cfg = Config.configure_scenario("scenario", task_configs=[build_msg_task_cfg])

################################################################
#            Design graphical interface                        #
################################################################

input_name = "Taipy"
message = None


def submit_scenario(state):
    state.scenario.input_name.write(state.input_name)
    state.scenario.submit()
    state.message = scenario.message.read()

# x values are [-10..10]
x_range = range(-10, 11)

import pandas as pd
import numpy as np

# Generate dates

start_date = "2023-12-17"  # Replace "<start-date>" with your actual start date
dates = pd.date_range(start=start_date, periods=5, freq="2W")
print(dates)

# Generate donation amounts (example data)
donation_amounts = [100, 50, 50, 25, 75]


# Convert dates to datetime objects
print(donation_amounts)

# Create a DataFrame with dates and donation amounts
data = {
    "Date": dates,
    "Donation Amount ($)": donation_amounts
}

page = """

<|{data}|chart|mode=lines|x=Date|y=Donation Amount ($)|>

"""


if __name__ == "__main__":
    ################################################################
    #            Instantiate and run Core service                  #
    ################################################################
    Core().run()

    ################################################################
    #            Manage scenarios and data nodes                   #
    ################################################################
    scenario = tp.create_scenario(scenario_cfg)

    ################################################################
    #            Instantiate and run Gui service                   #
    ################################################################

    # Define the stylekit with custom colors
    stylekit = {
        "color_primary": "#BADA55",  # Primary color
        "color_secondary": "#C0FFE",  # Secondary color
        "font_family": "Montserrat, sans-serif",  # Font family
        "font_size": "14px",  # Font size
        "background_color": "#FFFFFF",  # Background color (change to your desired color)
        "text_color": "#000000"  # Text color (change to your desired color)
    }

    # Pass the stylekit when running the GUI
    Gui(page).run(stylekit=stylekit)

