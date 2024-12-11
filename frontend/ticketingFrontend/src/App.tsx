import React from 'react';
import ConfigurationForm from './components/ConfigurationForm';
import TicketDisplay from './components/TicketDisplay';
import ControlPanel from './components/ControlPanel';

function App() {
  return (
    <div className="p-8">
      <h1 className="text-3xl font-bold text-center mb-8">
        Real-Time Ticketing System
      </h1>
      <ConfigurationForm />
      <TicketDisplay />
      <ControlPanel />
    </div>
  );
}

export default App;

