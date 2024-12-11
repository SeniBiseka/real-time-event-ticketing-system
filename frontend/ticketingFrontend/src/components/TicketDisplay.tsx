import React, { useState, useEffect } from 'react';
import api from '../axiosConfig';

const TicketDisplay: React.FC = () => {
  const [availableTickets, setAvailableTickets] = useState(0);

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const response = await api.get('/api/tickets/available');
        setAvailableTickets(response.data);
      } catch (error) {
        console.error('Error fetching available tickets:', error);
      }
    };

    fetchTickets();
    const interval = setInterval(fetchTickets, 5000); // Poll every 5 seconds
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="mt-8">
      <h2 className="text-xl font-semibold">Available Tickets</h2>
      <p className="text-2xl font-bold">{availableTickets}</p>
    </div>
  );
};

export default TicketDisplay;
