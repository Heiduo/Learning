'''
@Description: 
@Version: 2.0
@Autor: Heiduo
@Date: 2020-05-29 10:36:28
@LastEditTime: 2020-05-29 12:14:39
@Contact: heiduox@163.com
'''
import os
class Solution:
    def rob(self, nums) -> int:
        maxNum = 0
        i= 0
        # if(len(nums)<=2):
        #     return max(nums)
        
        for temp1 in nums:
            num  = self.dp(i,temp1,nums)
            maxNum = num if num > maxNum else maxNum
            i+=1
        
        return maxNum

    def dp(self,i:int,maxNum:int,nums) -> int:
        if i >= len(nums)-2:
            return maxNum
        maxList = []
        j = 0
        for temp in nums[i+2:]:
            temp2 = maxNum + temp
            # temp2= self.dp(i+2,temp2,nums)
            maxList.append(self.dp(i+j+2,temp2,nums))
            j+=1
            # maxNum = temp2 if temp2>maxNum else maxNum
        return max(maxList)



testList = [183,219,57,193,94,233,202,154,65,240,97,234,100,249,186,66,90,238,168,128,177,235,50,81,185,165,217,207,88,80,112,78,135,62,228,247,211]
sl = Solution()
print(sl.rob(testList))