import React, { useState } from 'react';
import api from '../axiosConfig';

const ConfigurationForm: React.FC = () => {
  const [config, setConfig] = useState({
    totalTickets: 5,
    ticketReleaseRate: 2,
    customerRetrievalRate: 1,
    maxTicketCapacity: 9,
  });

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
        await api.post('/api/tickets/config', config);
        alert('Configuration submitted successfully!');
    } catch (error) {
      console.error('Error submitting configuration:', error);
      alert('Failed to submit configuration.');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setConfig({ ...config, [name]: Number(value) });
  };

  return (
    <form className="space-y-4" onSubmit={handleSubmit}>
      {Object.keys(config).map((key) => (
        <div key={key}>
          <label className="block text-sm font-medium">{key}</label>
          <input
            type="number"
            name={key}
            value={config[key as keyof typeof config]}
            onChange={handleChange}
            className="border p-2 rounded w-full"
          />
        </div>
      ))}
      <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded">
        Submit Configuration
      </button>
    </form>
  );
};

export default ConfigurationForm;
