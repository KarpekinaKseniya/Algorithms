package org.example.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    In a project, you have a list of required skills req_skills, and a list of people.
    The i-th person people[i] contains a list of skills that the person has.
    Consider a sufficient team: a set of people such that for every required skill in req_skills,
    there is at least one person in the team who has that skill. We can represent these teams by
    the index of each person.
    For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
    Return any sufficient team of the smallest possible size, represented by the index of each person.
    You may return the answer in any order.
    It is guaranteed an answer exists.
*/
public class SufficientTeam {

    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        int n = req_skills.length;
        Map<String, Integer> skillMap = new HashMap<>();
        int skillIndex = 0;
        for (String skill : req_skills) {
            skillMap.put(skill, skillIndex++);
        }
        int target = (1 << n) - 1;
        int[] dp = new int[target + 1];
        List<Integer>[] teams = new List[target + 1];
        teams[0] = new ArrayList<>();
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        int personIndex = 0;
        for (List<String> person : people) {
            int mask = 0;
            for (String skill : person) {
                if (skillMap.containsKey(skill)) {
                    mask |= (1 << skillMap.get(skill));
                }
            }
            for (int prev = 0; prev <= target; prev++) {
                if (dp[prev] != Integer.MAX_VALUE && (prev | mask) != prev) {
                    int currTeam = prev | mask;
                    if (dp[prev] + 1 < dp[currTeam]) {
                        dp[currTeam] = dp[prev] + 1;
                        teams[currTeam] = new ArrayList<>(teams[prev]);
                        teams[currTeam].add(personIndex);
                    }
                }
            }
            personIndex++;
        }
        List<Integer> teamMembers = teams[target];
        int[] result = new int[teamMembers.size()];
        for (int i = 0; i < teamMembers.size(); i++) {
            result[i] = teamMembers.get(i);
        }
        return result;
    }

}
