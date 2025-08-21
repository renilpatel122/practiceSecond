import { useState } from 'react';
import axios from '../services/api';

export default function OrderForm() {
  const [order, setOrder] = useState({
    stockSymbol: '',
    orderType: 'BUY',
    quantity: 1,
    price: 0,
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/api/orders', order);
      alert('Order placed!');
    } catch (err) {
      alert('Failed to place order');
    }
  };

  return (
    <form onSubmit={handleSubmit} className="p-4 border rounded-xl max-w-md">
      <h2 className="text-xl mb-2 font-semibold">Place Order</h2>
      <input type="text" placeholder="Stock Symbol" className="input" value={order.stockSymbol}
        onChange={(e) => setOrder({ ...order, stockSymbol: e.target.value })} />
      <select value={order.orderType} onChange={(e) => setOrder({ ...order, orderType: e.target.value })}>
        <option>BUY</option>
        <option>SELL</option>
      </select>
      <input type="number" placeholder="Quantity" className="input"
        value={order.quantity} onChange={(e) => setOrder({ ...order, quantity: +e.target.value })} />
      <input type="number" placeholder="Price" className="input"
        value={order.price} onChange={(e) => setOrder({ ...order, price: +e.target.value })} />
      <button type="submit" className="btn btn-primary mt-2">Submit</button>
    </form>
  );
}
