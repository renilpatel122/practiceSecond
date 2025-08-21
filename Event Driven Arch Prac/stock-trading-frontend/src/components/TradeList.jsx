import { useEffect, useState } from 'react';
import { connectWebSocket } from '../services/websocket';

export default function TradeList() {
  const [trades, setTrades] = useState([]);

  useEffect(() => {
    const ws = connectWebSocket((tradeEvent) => {
      setTrades((prev) => [tradeEvent, ...prev.slice(0, 10)]);
    });
    return () => ws.close();
  }, []);

  return (
    <div className="p-4">
      <h2 className="text-xl font-semibold">Live Trades</h2>
      <ul>
        {trades.map((t, i) => (
          <li key={i}>{t.stockSymbol} | {t.price} | {t.quantity}</li>
        ))}
      </ul>
    </div>
  );
}
