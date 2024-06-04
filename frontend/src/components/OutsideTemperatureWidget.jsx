import React, {useEffect, useContext} from "react";
import {Card, CardContent, Typography} from "@material-ui/core";
import Box from "@mui/material/Box";
import CircularProgress from '@material-ui/core/CircularProgress';
import AppContext from "../context/AppContext.jsx";
import {initializeTemperatureFetching} from "../context/TemperatureActions.jsx";


function OutsideTemperatureWidget() {
    const {state, dispatch} = useContext(AppContext);
    const {temperature} = state;
    const {loading, error, data} = temperature;

    useEffect(() => {
        const clearTimers = initializeTemperatureFetching(dispatch);

        // Cleanup function to clear the timeout and interval
        return () => {
            clearTimers();
        };
    }, [dispatch]);

    const renderContent = () => {
        if (loading === true && error === null) {
            return <CircularProgress/>;
        } else if (error !== null) {
            // Truncate the error message to fit within the box
            const truncatedError = error.length > 50 ? error.substring(0, 50) + '...' : error;
            return (
                <Typography variant="h6" style={{textAlign: 'center'}}>
                    Error: {truncatedError}
                </Typography>
            );
        } else if (data) {
            return <Typography variant="h6" component="h2">Outside temperature: {data}</Typography>;
        } else {
            return null; // Render nothing if data is not available
        }
    };

    return (
        <Box>
            <Card
                style={{
                    backgroundColor: "lightgray",
                    border: "1px solid black",
                    borderRadius: "10px",
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center",
                    justifyContent: "center",
                    height: 100,
                }}
            >
                <CardContent>
                    {renderContent()}
                </CardContent>
            </Card>
        </Box>
    );
}

export default OutsideTemperatureWidget;
