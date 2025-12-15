export interface Message {
  sender: 'user' | 'bot';
  text: string;
  loading?: boolean;
};
