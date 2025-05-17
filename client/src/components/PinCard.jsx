import { Card } from '@/components/ui/card'

const PinCard = ({ imageUrl }) => (
  <Card className="overflow-hidden rounded-2xl shadow-md">
    <img
      src={imageUrl}
      alt="pin"
      className="w-full object-cover"
      loading="lazy"
    />
  </Card>
)

export default PinCard
