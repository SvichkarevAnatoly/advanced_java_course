package edu.technopolis.homework;

public enum Command {
    ATTACK{
        public String toString() {
            return "attack";
        }
    },
    DEFENCE{
        public String toString() {
            return "defend";
        }
    },
    SUPER_ATTACK {
        public String toString() {
            return "super attack";
        }
    },
    NOTHING{
        public String toString() {
            return "do nothing";
        }
    }
}
