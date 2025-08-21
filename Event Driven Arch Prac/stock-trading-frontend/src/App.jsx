import OrderForm from './components/OrderForm';
import TradeList from './components/TradeList';
import TradeHistory from './components/TradeHistory';

export default function App() {
  return (
    <div className="p-4 space-y-4">
      <OrderForm />
      <TradeList />
      <TradeHistory />
    </div>
  );
}
