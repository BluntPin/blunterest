import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const App = () => {
  const [pins, setPins] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch pins from the backend API
    axios.get('http://localhost:8080/api/pins')
      .then(response => {
        setPins(response.data);
        setLoading(false);
      })
      .catch(err => {
        setError('Error fetching data');
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="app-container">
      <header>
        <h1>Pinterest Clone</h1>
      </header>
      <main>
        <div className="pins-container">
          {pins.map(pin => (
            <div key={pin.id} className="pin">
              <img src={pin.imageUrl} alt={pin.title} />
            </div>
          ))}
        </div>
      </main>
    </div>
  );
};

export default App;
