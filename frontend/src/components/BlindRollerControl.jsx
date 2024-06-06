import React, { useContext, useState } from 'react';
import AppContext from '../context/AppContext.jsx';
import { Button, TextField, Typography, Box, Grid } from '@mui/material';
import { setBlindRoller } from "../context/Actions.jsx";

const BlindRollerControl = ({ deviceId }) => {
    const { state, dispatch } = useContext(AppContext);
    const [value, setValue] = useState('');

    // Safely access actuators data
    const actuators = state.actuators || { data: [] }; // Ensure actuators object and data array are defined
    const actuatorId = actuators.data.length > 0 ? actuators.data[0].id : null;

    const handleValueChange = (event) => {
        setValue(event.target.value);
    };

    const handleSubmit = () => {
        if (value && actuatorId) {
            setBlindRoller(dispatch, deviceId, actuatorId, value);
        } else {
            console.error('Actuator ID is not available or invalid input.');
        }
    };

    return (
        <Box sx={{
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
            justifyContent: 'center',
            mt: 2,
            mb: 2,
            padding: 2,
            bgcolor: 'background.paper',
            borderRadius: '10px',
            boxShadow: 3,
            maxWidth: 400,
            margin: 'auto',
        }}>
            <Typography variant="h6" gutterBottom>
                Set Blind Roller Position
            </Typography>
            <Grid container spacing={2} justifyContent="center">
                <Grid item xs={12}>
                    <TextField
                        label="Position"
                        variant="outlined"
                        type="number"
                        value={value}
                        onChange={handleValueChange}
                        fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <Button
                        variant="contained"
                        color="primary"
                        onClick={handleSubmit}
                        disabled={!actuatorId || value === ''}
                        fullWidth
                    >
                        Set Position
                    </Button>
                </Grid>
            </Grid>
        </Box>
    );
};

export default BlindRollerControl;