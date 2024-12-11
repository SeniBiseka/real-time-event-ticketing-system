import React, { useState } from 'react';
import api from '../axiosConfig';

const ControlPanel: React.FC = () => {
  const [customerQuantity, setCustomerQuantity] = useState(1);

  const handleStart = async () => {
    try {
        await api.post('/api/tickets/start', null, {
            params: { customerQuantity },
          });          
      alert('Ticketing system started successfully!');
    } catch (error) {
      console.error('Error starting the system:', error);
      alert('Failed to start the system.');
    }
  };

  return (
    <div className="mt-8">
      <label className="block text-sm font-medium">Customer Quantity</label>
      <input
        type="number"
        value={customerQuantity}
        onChange={(e) => setCustomerQuantity(Number(e.target.value))}
        className="border p-2 rounded w-full"
      />
      <button
        onClick={handleStart}
        className="bg-green-500 text-white py-2 px-4 rounded mt-4"
      >
        Start System
      </button>
    </div>
  );
};

export default ControlPanel;