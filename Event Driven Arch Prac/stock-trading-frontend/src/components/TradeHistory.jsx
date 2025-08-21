import { useEffect, useState } from 'react';
import axios from '../services/api';

export default function TradeHistory() {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    axios.get('/api/trades').then(res => setHistory(res.data));
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold">Trade History</h2>
      <ul>
        {history.map((t) => (
          <li key={t.tradeId}>
            {t.stockSymbol} | {t.price} | {t.quantity}
          </li>
        ))}
      </ul>
    </div>
  );
}
