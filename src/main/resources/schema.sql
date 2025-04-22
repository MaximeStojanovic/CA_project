CREATE TYPE direction_type AS ENUM ('INBOUND', 'OUTBOUND');
CREATE TYPE processed_flow_type AS ENUM ('MESSAGE', 'ALERTING', 'NOTIFICATION');
CREATE TYPE message_status_type AS ENUM ('RECEIVED', 'PROCESSED', 'ERROR');

CREATE TABLE partner (
    id SERIAL PRIMARY KEY,
    alias VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    direction direction_type NOT NULL,
    application VARCHAR(255),
    processed_flow_type processed_flow_type NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Add unique constraint on alias
ALTER TABLE partner ADD CONSTRAINT uk_partner_alias UNIQUE (alias);

-- Create index on frequently queried columns
CREATE INDEX idx_partner_alias ON partner(alias);
CREATE INDEX idx_partner_direction ON partner(direction);
CREATE INDEX idx_partner_processed_flow_type ON partner(processed_flow_type);

-- Messages table
CREATE TABLE messages (
    id VARCHAR(255) PRIMARY KEY,
    content TEXT,
    sender VARCHAR(255),
    recipient VARCHAR(255),
    timestamp TIMESTAMP,
    status message_status_type,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for messages table
CREATE INDEX idx_messages_timestamp ON messages(timestamp);
CREATE INDEX idx_messages_status ON messages(status);
CREATE INDEX idx_messages_sender ON messages(sender);
CREATE INDEX idx_messages_recipient ON messages(recipient); 