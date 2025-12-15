export interface Message {
  sender: 'USER' | 'BOT';
  text: string;
  loading?: boolean;
};
